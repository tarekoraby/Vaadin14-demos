package org.vaadin.tarek.textArea;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("TextAreaView")
@CssImport(value = "./styles/vaadin-text-area-styles.css", themeFor = "vaadin-text-area")
public class TextAreaView extends VerticalLayout {

    public TextAreaView() {
        TextArea wrappingTextArea = new TextArea("Wrapping TA");

        TextArea nonWrappingTextArea = new TextArea("Non-Wrapping TA");
        nonWrappingTextArea.setClassName("non-wrapping");

        add(wrappingTextArea, nonWrappingTextArea);
    }

}
