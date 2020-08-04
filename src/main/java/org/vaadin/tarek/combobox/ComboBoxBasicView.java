package org.vaadin.tarek.combobox;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("ComboBoxBasicView")
@CssImport(value = "./styles/vaadin-combobox-overlay.css", themeFor = "vaadin-combo-box-overlay")
public class ComboBoxBasicView extends VerticalLayout {

    public ComboBoxBasicView() {
        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.setItems("Option one", "Option two");
        labelComboBox.setLabel("Label");

        ComboBox<String> placeHolderComboBox = new ComboBox<>();
        placeHolderComboBox.setItems("Option one", "Option two");
        placeHolderComboBox.setPlaceholder("Placeholder");

        ComboBox<String> valueComboBox = new ComboBox<>();
        valueComboBox.setItems("Option one", "Option two", "Option three");
        valueComboBox.setValue("Option one");

        ComboBox<String> wideOverlayComboBox = new ComboBox<>();
        wideOverlayComboBox.setItems("Option one", "Option two");
        wideOverlayComboBox.getElement().setAttribute("theme",
                "wide-combobox-overlay");

        ComboBox<String> filteringComboBox = new ComboBox<>();
        List<String> elementsList = new ArrayList<>();
        elementsList.add("Sun");
        elementsList.add("Sµn");
        elementsList.add("µmbrella");
        elementsList.add("umbrella");
        elementsList.add("Other text");

        ItemFilter<String> filter = (element, filterString) -> {
            if (filterString.contains("µ") || filterString.contains("u")) {
                element = element.replace("µ", "u");
                return element.startsWith(filterString.replace("µ", "u"));
            } else {
                return element.startsWith(filterString);
            }
        };

        filteringComboBox.setItems(filter, elementsList);
        filteringComboBox.setClearButtonVisible(true);

        add(labelComboBox, placeHolderComboBox, valueComboBox,
                wideOverlayComboBox, filteringComboBox);
    }
}
