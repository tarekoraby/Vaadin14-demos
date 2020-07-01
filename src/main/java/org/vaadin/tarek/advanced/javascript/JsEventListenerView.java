package org.vaadin.tarek.advanced.javascript;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("JsEventListenerView")
public class JsEventListenerView extends VerticalLayout {

    Paragraph p;

    public JsEventListenerView() {
        TextField tf = new TextField("Label");

        tf.getElement().executeJs(
                "this.addEventListener(\"mouseover\", () => $0.$server.textFieldMouseOver())",
                getElement());
        tf.getElement().executeJs(
                "this.addEventListener(\"click\", () => $0.$server.textFieldClicked())",
                getElement());
        tf.getElement().executeJs(
                "this.addEventListener(\"mouseout\", () => $0.$server.textFieldMouseOut())",
                getElement());

        p = new Paragraph("Initial element status");
        add(tf, p);
    }

    @ClientCallable
    public void textFieldMouseOver() {
        p.setText("Mouse over element");
    }

    @ClientCallable
    public void textFieldClicked() {
        p.setText("Element clicked");
    }

    @ClientCallable
    public void textFieldMouseOut() {
        p.setText("Mouse out of element");
    }
}