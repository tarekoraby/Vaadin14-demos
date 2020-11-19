package org.vaadin.tarek.components.custom.customevent;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;

@Route("CustomEventView")
public class CustomEventView extends VerticalLayout {

    public CustomEventView() {
        Element button = ElementFactory.createButton("Fire custom event");
        button.addEventListener("click", e -> {
            fireEvent(new CustomEvent(this, false));
        });
        getElement().appendChild(button);

        addListener(CustomEvent.class,
                e -> System.out.println("my-event fired"));

        getElement().addEventListener("my-event",
                e -> System.out.println("my-event received from client side"));
    }
}
