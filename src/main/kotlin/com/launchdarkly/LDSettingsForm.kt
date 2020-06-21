package com.launchdarkly

import com.intellij.ui.JBIntSpinner
import com.intellij.ui.components.CheckBox
import com.intellij.ui.layout.panel
import javax.swing.*

fun settingsPanel(): JPanel {
    // we use growX to test right border
    return panel {
        row("Project:") { JTextField("text")() }
        row("Environment:") { JTextField("text")() }
        row("API Key:") { JPasswordField("secret")() }
    }
}