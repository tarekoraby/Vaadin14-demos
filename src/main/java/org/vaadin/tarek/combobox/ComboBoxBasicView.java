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
@CssImport(value = "./styles/vaadin-combo-box-item-styles.css", themeFor = "vaadin-combo-box-item")
public class ComboBoxBasicView extends VerticalLayout {

    public ComboBoxBasicView() {
        ComboBox<String> simpleComboBox = new ComboBox<>();
        simpleComboBox.setItems("Option one", "Option two", "Option three");
        simpleComboBox.setValue("Option one");
        simpleComboBox.setLabel("Simple ComboBox");

        ComboBox<String> wideOverlayComboBox = new ComboBox<>();
        wideOverlayComboBox.setItems("Option one", "Option two");
        wideOverlayComboBox.setPlaceholder("Placeholder");
        wideOverlayComboBox.getElement().setAttribute("theme",
                "wide-combobox-overlay");
        wideOverlayComboBox.setLabel("Wide-Overlay ComboBox");

        ComboBox<String> condensedListComboBox = new ComboBox<>();
        condensedListComboBox.setItems("Option one", "Option two");
        condensedListComboBox.getElement().setAttribute("theme",
                "condensed-list-combobox");
        condensedListComboBox.setLabel("Condensed-List ComboBox");

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
        filteringComboBox.setLabel("Filtering ComboBox");

        add(simpleComboBox, wideOverlayComboBox, condensedListComboBox,
                filteringComboBox);
    }
}
