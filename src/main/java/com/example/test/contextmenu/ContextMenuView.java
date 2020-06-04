package com.example.test.contextmenu;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ContextMenuView")
public class ContextMenuView extends VerticalLayout {

    public ContextMenuView() {

        // Use TextField for standard text input
        TextField textField = new TextField("Text Field with context-menu");

        ContextMenu contextMenu = new ContextMenu();

        contextMenu.setTarget(textField);

        Label message = new Label("-");

        contextMenu.addItem("First menu item",
                e -> message.setText("Clicked on the first item"));

        contextMenu.addItem("Second menu item",
                e -> message.setText("Clicked on the second item"));

// The created MenuItem component can be saved for later use
        MenuItem item = contextMenu.addItem("Disabled menu item",
                e -> message.setText("This cannot happen"));
        item.setEnabled(false);

        add(textField, message);
    }
}
