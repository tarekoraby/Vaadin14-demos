package com.example.test.radio;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("radio")
public class RadioButtonGroupView extends VerticalLayout {

    public RadioButtonGroupView() {
    	RadioButtonGroup<String> radio = new RadioButtonGroup<>();
    	radio.setLabel("Horizontal");
    	radio.setItems("Option one", "Option two", "Option three");
    	radio.setValue("Option one");

        add(radio);
    }
}

