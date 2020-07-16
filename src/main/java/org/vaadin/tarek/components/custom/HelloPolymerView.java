package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("HelloPolymerView")
public class HelloPolymerView extends VerticalLayout {

    public HelloPolymerView() {
        HelloPolymer elem = new HelloPolymer();
        add(elem);

        elem.setContent(new Label("Label inside bound div"));
        elem.getElement()
                .appendChild(
                        (new TextField("TextField inside span")).getElement());
    }
}
