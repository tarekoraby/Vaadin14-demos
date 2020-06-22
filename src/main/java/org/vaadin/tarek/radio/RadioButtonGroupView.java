package org.vaadin.tarek.radio;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("radioview")
public class RadioButtonGroupView extends VerticalLayout {

    public RadioButtonGroupView() {
        RadioButtonGroup<String> radioOne = new RadioButtonGroup<>();
        radioOne.setLabel("Horizontal RadioButtonGroup");
        radioOne.setItems("Option one", "Option two", "Option three");
        radioOne.setValue("Option one");

        RadioButtonGroup<String> radioTwo = new RadioButtonGroup<>();
        radioTwo.setLabel(
                "Horizontal RadioButtonGroup with custom buttons style");
        radioTwo.setItems("Option one", "Option two", "Option three");
        radioTwo.setValue("Option one");

        radioTwo.getChildren().forEach(child -> child.getElement().getStyle()
                .set("padding-right", "80px"));

        add(radioOne, radioTwo);
    }
}
