package org.vaadin.tarek.advanced;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ExcuteJSView")
public class ExcuteJSView extends VerticalLayout {


    public ExcuteJSView() {
        Button button = new Button("Hide me!!");

        button.addClickListener(event -> {
            button.getElement()
            .executeJs(
                    "this.style.display = \"none\"");
        });

        add(button);
    }
}
