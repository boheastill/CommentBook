package com.github.boheastill.pd2

import com.intellij.openapi.components.ApplicationComponent

/*^l^*/
class A : ApplicationComponent {
    // Returns the component name (any unique string value).
    override fun getComponentName(): String {
        return "MyPlugin"
    }

    // If you register the MyPluginRegistration class in the <application-components> section of
    // the plugin.xml file, this method is called on IDEA start-up.
    override fun initComponent() {
        println("初始化组件")
        /*        ActionManager am = ActionManager.getInstance();
        TextBoxes action = new TextBoxes();
        // Passes an instance of your custom TextBoxes class to the registerAction method of the ActionManager class.
        am.registerAction("MyPluginAction", action);
        // Gets an instance of the WindowMenu action group.
        DefaultActionGroup windowM = (DefaultActionGroup) am.getAction("WindowMenu");
        // Adds a separator and a new menu command to the WindowMenu group on the main menu.
        windowM.addSeparator();
        windowM.add(action);*/
    }

    // Disposes system resources.
    override fun disposeComponent() {}
}