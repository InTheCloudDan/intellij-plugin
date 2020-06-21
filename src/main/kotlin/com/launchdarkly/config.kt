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
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.util.ui.FormBuilder
import com.intellij.util.ui.UIUtil
import com.intellij.util.xmlb.XmlSerializerUtil
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.*

class LaunchDarklyState {
    var fontSize = 24
    var hideDelay = 4*1000
}

@State(name = "LaunchDarklyConfig", storages = arrayOf(Storage(file = "launchdarkly.xml")))
class LaunchDarklyConfig : PersistentStateComponent<LaunchDarklyState>, Disposable {
    val configuration = LaunchDarklyState()

    override fun getState() = configuration
    override fun loadState(p: LaunchDarklyState) {
        XmlSerializerUtil.copyBean(p, configuration)
    }

    override fun dispose() {}
}

fun getLaunchDarkly(): LaunchDarklyConfig = ServiceManager.getService(LaunchDarklyConfig::class.java)

class LaunchDarklyConfigurable : Configurable, SearchableConfigurable {
    private val mainPanel: JPanel = settingsPanel()

    override fun getId() = displayName
    override fun enableSearch(option: String?) = null
    override fun getDisplayName() = "LaunchDarkly Plugin"
    override fun getHelpTopic() = null

    override fun createComponent() = mainPanel

    override fun isModified() = true

    override fun apply() {}

    override fun reset() {}

    override fun disposeUIResources() {
    }

    private fun isDigitsOnly(string: String): Boolean {
        return string.all { c -> c.isDigit() }
    }
}