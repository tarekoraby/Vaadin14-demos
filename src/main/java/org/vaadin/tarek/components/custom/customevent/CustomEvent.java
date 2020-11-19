package org.vaadin.tarek.components.custom.customevent;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("my-event")
public class CustomEvent extends ComponentEvent<Component> {
    public CustomEvent(Component source, boolean fromClient) {
        super(source, fromClient);
    }
}
