package com.launchdarkly

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.


class FeatureFlagWindowFactory : ToolWindowFactory {
    // Create the tool window content.
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow
    ) {
        val myToolWindow = FeatureFlagWindow(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content =
            contentFactory.createContent(myToolWindow.content, "", false)
        toolWindow.contentManager.addContent(content)
    }
}