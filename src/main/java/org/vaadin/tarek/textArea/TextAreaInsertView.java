package org.vaadin.tarek.textArea;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("TextAreaInsertView")
public class TextAreaInsertView extends VerticalLayout {

    public TextAreaInsertView() {
        TextArea textArea = new TextArea();
        textArea.setValue(getLoremIpsum());
        textArea.setWidth("500px");
        textArea.setHeight("500px");

        TextField tf = new TextField();
        tf.setValue("Some text to be pasted in TA");

        Button button = new Button("Insert at caret", e -> insertAtCaret(textArea, tf.getValue()));

        add(textArea, tf, button);
    }

    void insertAtCaret(TextArea textArea, String text) {

        textArea.getElement().executeJs(
                "var textareaElement = $0.shadowRoot.querySelector('textarea');"
                + "textareaElement.insertAtCaret = function (text) {"
                + "  text = text || '';"
                + "    var startPos = this.selectionStart;"
                + "    var endPos = this.selectionEnd;"
                + "    this.value = this.value.substring(0, startPos) +"
                + "      text +"
                + "      this.value.substring(endPos, this.value.length);"
                + "    this.selectionStart = startPos + text.length;"
                + "    this.selectionEnd = startPos + text.length;"
                + "};"
                + "textareaElement.insertAtCaret('" + text + "');"
                + "$0.value = textareaElement.value;"
                + "$0.dispatchEvent(new Event('change'));"
                , textArea.getElement());

    }

    private String getLoremIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt"
                + " ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"
                + " ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
                + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur"
                + " sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id"
                + " est laborum.";
    }

}
