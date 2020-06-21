package com.launchdarkly

import com.intellij.ui.JBIntSpinner
import com.intellij.ui.components.CheckBox
import com.intellij.ui.layout.panel
import com.intellij.openapi.ui.DialogPanel
import javax.swing.*

fun settingsPanel(field: JTextField): JPanel {
    // we use growX to test right border
    return panel {
        row("Project:") { field() }
        row("Environment:") { JTextField("text")() }
        row("API Key:") { JPasswordField("secret")() }
    }
}