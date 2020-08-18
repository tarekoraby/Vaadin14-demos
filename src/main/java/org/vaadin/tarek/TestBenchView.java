package org.vaadin.tarek;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("TestBenchView")
public class TestBenchView extends VerticalLayout {

    public TestBenchView() {
        TextField tf = new TextField();

        Dialog dialog = new Dialog();
        dialog.add(new Label("Close me with the esc-key or an outside click"));
        dialog.addDialogCloseActionListener(e -> {
            tf.setValue("myValue");
            dialog.close();
        });
        dialog.open();

        add(tf);
    }

}
