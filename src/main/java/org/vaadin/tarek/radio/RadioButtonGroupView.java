package org.vaadin.tarek.radio;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("radioview")
@CssImport(value = "./styles/vaadin-radio-button-styles.css", themeFor = "vaadin-radio-button")
public class RadioButtonGroupView extends VerticalLayout {

    public RadioButtonGroupView() {
        RadioButtonGroup<String> radioOne = new RadioButtonGroup<>();
        radioOne.setLabel("Horizontal RadioButtonGroup");
        radioOne.setItems("Option one", "Option two", "Option three");
        radioOne.setValue("Option one");

        RadioButtonGroup<String> radioTwo = new RadioButtonGroup<>();
        radioTwo.setLabel("Horizontal RadioButtonGroup with custom buttons style");
        radioTwo.setItems("Option one", "Option two", "Option three");
        radioTwo.setValue("Option one");

        radioTwo.getChildren().forEach(child -> child.getElement().getStyle().set("padding-right", "80px"));

        RadioButtonGroup<String> swappedGroup = new RadioButtonGroup<>();
        swappedGroup.setLabel("Horizontal RadioButtonGroup");
        swappedGroup.setItems("Option one", "Option two", "Option three");
        swappedGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        swappedGroup.getChildren().forEach(elem -> ((HasStyle) elem).addClassName("swapped"));

        add(radioOne, radioTwo, swappedGroup);
    }
}
