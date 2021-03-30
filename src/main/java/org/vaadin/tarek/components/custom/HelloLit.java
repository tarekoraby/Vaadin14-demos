package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;

@Tag("hello-lit")
@JsModule("./src/themable-element.ts")
@JsModule("./src/hello-lit.ts")
@CssImport(value = "./styles/hello-lit-styles.css", themeFor = "hello-lit")
public class HelloLit extends LitTemplate {

    public HelloLit() {
    }
}