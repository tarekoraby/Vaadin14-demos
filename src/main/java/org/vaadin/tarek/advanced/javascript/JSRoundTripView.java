package org.vaadin.tarek.advanced.javascript;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;

@Route("JSRoundTripView")
public class JSRoundTripView extends VerticalLayout {

    Paragraph p;

    public JSRoundTripView() {
        p = new Paragraph("Some initial text");
        Button button = new Button("Change text though server round-trip");

        button.addClickListener(event -> {
            String str = "Different text from server";
            Page page = UI.getCurrent().getPage();
            page.executeJs("$0.$server.addItem(\"" + str + "\")", getElement());
        });

        add(p, button);
    }

    @ClientCallable
    public void addItem(String str) {
        p.setText(str);
    }
}
