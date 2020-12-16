package org.vaadin.tarek.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("DialogAutoCloseView")
public class DialogAutoCloseView extends VerticalLayout {

    public DialogAutoCloseView() {
        Button button = new Button("Open Dialog");
        Dialog dialog = new Dialog();
        dialog.add(new Span("I will close automatically after 3 seconds"));

        button.addClickListener(event -> {
            dialog.open();
            dialog.getElement().executeJs(
                    "setTimeout(function(){ document.getElementsByTagName('vaadin-dialog-overlay')[0].opened=false;}, 3000); ");
        });

        add(button);
    }

}
