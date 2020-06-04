package com.example.test.combobox;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("ComboBoxBasicView")
public class ComboBoxBasicView extends VerticalLayout {

    public ComboBoxBasicView() {
        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.setItems("Option one", "Option two");
        labelComboBox.setLabel("Label");

        ComboBox<String> placeHolderComboBox = new ComboBox<>();
        placeHolderComboBox.setItems("Option one", "Option two");
        placeHolderComboBox.setPlaceholder("Placeholder");

        ComboBox<String> valueComboBox = new ComboBox<>();
        valueComboBox.setItems("Value", "Option one", "Option two");
        valueComboBox.setValue("Value");

        add(labelComboBox, placeHolderComboBox, valueComboBox);
    }
}
