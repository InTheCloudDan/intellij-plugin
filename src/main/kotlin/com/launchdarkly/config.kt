package com.launchdarkly

import com.intellij.ide.AppLifecycleListener
import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.keymap.Keymap
import com.intellij.openapi.keymap.KeymapManager
import com.intellij.openapi.keymap.ex.KeymapManagerEx
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.layout.panel
import com.intellij.util.ui.FormBuilder
import com.intellij.util.ui.UIUtil
import com.intellij.util.xmlb.XmlSerializerUtil
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.TextField
import javax.swing.*

//class LaunchDarklySettings {
//    var project: String = ""
//    var environment = ""
//}

@State(name = "LaunchDarklyConfig", storages = arrayOf(Storage(file = "launchdarkly.xml")))
class LaunchDarklyConfig : PersistentStateComponent<LaunchDarklyConfig.State> {
    var settings: State = State()

    override fun getState(): State? {
        return State(state!!.project, state!!.environment)
    }

    override fun loadState(state: State) {
        state.project = state.project ?: ""
        state.environment = state.environment ?: ""

    }

    data class State(
        var project: String = "",
        var environment: String = ""
    )
}

class LaunchDarklyConfigurable() : BoundConfigurable(displayName = "LaunchDarkly Plugin") {
    private val settings = ServiceManager.getService(LaunchDarklyConfig::class.java)
    private val project = JTextField("")

    override fun getHelpTopic() = null

    override fun createPanel(): DialogPanel {
        return panel {
            row("Project:") { project() }
            row("Environment:") { JTextField("text")() }
            row("API Key:") { JPasswordField("secret")() }
        }
    }

    override fun isModified(): Boolean {
        return project.text != "blue"
    }

    override fun apply() {
        settings.settings?.project = project?.text
    }

    private fun isDigitsOnly(string: String): Boolean {
        return string.all { c -> c.isDigit() }
    }
}