package org.vaadin.tarek.rte;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.router.Route;

@Route("RichTextEditorView")
public class RichTextEditorView extends VerticalLayout {

    public RichTextEditorView() {
        Div valueBlock = new Div();
        RichTextEditor rte = new RichTextEditor();
        Button saveBtn = new Button("Save value", e -> valueBlock.setText(rte.getValue()));
        Button setBtn = new Button("Set value", e -> rte.setValue(valueBlock.getText()));

        add(rte, saveBtn, setBtn, valueBlock);
    }

}
