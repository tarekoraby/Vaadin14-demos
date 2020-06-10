package com.example.test.contextmenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ContextMenuViewWithHidableItems")
public class ContextMenuViewWithHidableItems extends VerticalLayout {

    public ContextMenuViewWithHidableItems() {

        // Use TextField for standard text input
        TextField textField = new TextField("Text Field with context-menu");

        ContextMenu contextMenu = new ContextMenu();

        contextMenu.setTarget(textField);

        Label message = new Label("-");

        MenuItem itemOne = contextMenu.addItem(
                "First menu item",
                e -> message.setText("Clicked on the first item"));

        contextMenu.addItem("Second menu item",
                e -> message.setText("Clicked on the second item"));

        Button button = new Button("Hide/show context-menu item");
        button.addClickListener(e -> {
            String displayStyle = itemOne.getElement().getStyle()
                    .get("display");
            if ((displayStyle == null) || displayStyle.equals("block")) {
                itemOne.getElement().getStyle().set("display", "none");
            } else {
                itemOne.getElement().getStyle().remove("display");
            }
        });

        add(textField, button, message);
    }
}
