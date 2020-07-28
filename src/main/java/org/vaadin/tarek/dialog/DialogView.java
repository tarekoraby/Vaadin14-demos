package org.vaadin.tarek.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("DialogView")
@CssImport(value = "./styles/vaadin-dialog-overlay.css", themeFor = "vaadin-dialog-overlay")
public class DialogView extends VerticalLayout {

    public DialogView() {
        Button button = new Button("Open Dialog");
        Dialog dialog = new Dialog();
        dialog.add(new Label("Close me with the esc-key or an outside click"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button.addClickListener(event -> dialog.open());

        add(button);
    }

}
