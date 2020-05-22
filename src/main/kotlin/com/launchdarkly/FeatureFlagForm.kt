package com.launchdarkly

import com.intellij.ui.JBIntSpinner
import com.intellij.ui.components.CheckBox
import com.intellij.ui.layout.panel
import com.intellij.util.ui.CheckBox
import javax.swing.*

fun visualPaddingsPanel(): JPanel {
    // we use growX to test right border
    return panel {
        row("Text field:") { JTextField("text")() }
        row("Password:") { JPasswordField("secret")() }
        row("Combobox:") { JComboBox<String>(arrayOf("one", "two"))(growX) }
        row("Combobox Editable:") {
            val field = JComboBox<String>(arrayOf("one", "two"))
            field.isEditable = true
            field(growX)
        }
        row("Button:") { button("label") {}.constraints(growX) }
        row("CheckBox:") { CheckBox("enabled")() }
        row("RadioButton:") { JRadioButton("label")() }
        row("Spinner:") { JBIntSpinner(0, 0, 7)() }
        row("Text with browse:") { textFieldWithBrowseButton("File") }
        // test text baseline alignment
        row("All:") {
            cell {
                JTextField("t")()
                JPasswordField("secret")()
                JComboBox<String>(arrayOf("c1", "c2"))(growX)
                button("b") {}
                CheckBox("c")()
                JRadioButton("rb")()
            }
        }
        row("Scroll pane:") {
            scrollPane(JTextArea("first line baseline equals to label"))
        }
    }
}