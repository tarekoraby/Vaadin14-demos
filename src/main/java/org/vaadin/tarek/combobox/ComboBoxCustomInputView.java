package org.vaadin.tarek.combobox;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("ComboBoxCustomInputView")
public class ComboBoxCustomInputView extends VerticalLayout {

    public ComboBoxCustomInputView() {
        ComboBoxCustomInput<String> combo = new ComboBoxCustomInput<>();
        combo.setItems("Option one", "Option two", "Option three");
        combo.setLabel("Allows prefix/suffix:");
        add(combo);

        String prefix = "$";
        String suffix = "%";
        combo.setPrefixComponent(new Span(prefix));
        combo.setSuffixComponent(new Span(suffix));

        /////////////////////////

        TextField prefixValue = new TextField("Prefix value:");
        prefixValue.setValue(prefix);
        prefixValue.addValueChangeListener(e -> combo
                .setPrefixComponent(new Span(prefixValue.getValue())));
        prefixValue.setValueChangeMode(ValueChangeMode.EAGER);
        add(prefixValue);

        TextField suffixValue = new TextField("Suffix value:");
        suffixValue.setValue(suffix);
        suffixValue.addValueChangeListener(e -> combo
                .setSuffixComponent(new Span(suffixValue.getValue())));
        suffixValue.setValueChangeMode(ValueChangeMode.EAGER);
        add(suffixValue);
    }
}
