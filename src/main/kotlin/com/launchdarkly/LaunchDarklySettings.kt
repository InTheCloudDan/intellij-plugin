package com.launchdarkly

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.util.xmlb.XmlSerializerUtil;
import javax.swing.JTextField


@State(name = "LaunchDarklyConfig", storages = arrayOf(Storage(file = "launchdarkly.xml")))
class LaunchDarklyConfig : PersistentStateComponent<LaunchDarklyConfig.State> {
    var settings: State = State()

    override fun getState(): State {
        return settings
    }

    override fun loadState(state: State) {
        XmlSerializerUtil.copyBean(state, this);
    }

    data class State(
        var project: String = "",
        var environment: String = ""
    )
}

class LaunchDarklyConfigurable(private val project: Project): BoundConfigurable(displayName = "LaunchDarkly Plugin") {
    private val settings = ServiceManager.getService(project, LaunchDarklyConfig::class.java)
    private val ldProject = JTextField(settings.settings.project)
    private var myPanel: DialogPanel = launchDarklySettingsPanel(ldProject)

    override fun getHelpTopic() = null

    override fun createPanel(): DialogPanel {
        return myPanel
    }
//        return panel {
//            row("Project:") { ldProject() }
//            row("Environment:") { JTextField("text")() }
//            row("API Key:") { JPasswordField("secret")() }
//        }

    override fun isModified(): Boolean {
        return settings.settings.project != ldProject.text
    }

    override fun apply() {
        myPanel.apply()
    }

//    override fun dispose() {
//        myPanel = null
//    }
}