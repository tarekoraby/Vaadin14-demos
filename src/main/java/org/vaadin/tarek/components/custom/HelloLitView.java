package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("HelloLitView")
public class HelloLitView extends VerticalLayout {

    public HelloLitView() {
        HelloLit elem = new HelloLit();
        add(elem);
    }
}
