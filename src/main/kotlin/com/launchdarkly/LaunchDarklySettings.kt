package com.launchdarkly

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import javax.swing.*

@State(name = "LaunchDarklyConfig", storages = arrayOf(Storage(file = "launchdarkly.xml")))
class LaunchDarklyConfig : PersistentStateComponent<LaunchDarklyConfig.State> {
    var settings: State = State()

    override fun getState(): State {
        return State(project = state.project, environment = state.environment)
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
    private val project = JTextField(settings.settings.project)

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
        settings.settings.project = project?.text

    }
}