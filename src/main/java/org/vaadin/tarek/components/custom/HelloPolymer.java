package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@JsModule("./src/hello-polymer.js")
@Tag("hello-polymer")
public class HelloPolymer extends PolymerTemplate<TemplateModel> {

    @Id("content")
    private Div content;

    public void setContent(Component component) {
        content.removeAll();
        content.add(component);
    }

    @EventHandler
    private void handleClick() {
        System.out.println("Received a click event");
    }

    @EventHandler
    private void handleChange(@EventData("event.srcElement.value") String value,
            @EventData("event.srcElement.checked") Boolean checked) {
        Notification.show("Change event received: " + value + ", isChecked: " + checked);
    }
}