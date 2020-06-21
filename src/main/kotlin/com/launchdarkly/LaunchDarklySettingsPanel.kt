package com.launchdarkly

import com.intellij.openapi.components.ServiceManager
import javax.swing.JPasswordField
import javax.swing.JTextField
import com.intellij.ui.layout.panel
import com.intellij.openapi.ui.*
import com.intellij.openapi.project.Project


fun launchDarklySettingsPanel(ldProject: JTextField): DialogPanel {
    return panel {
            row("Project:") { ldProject() }
            row("Environment:") { JTextField("text")() }
            row("API Key:") { JPasswordField("secret")() }
        }
}