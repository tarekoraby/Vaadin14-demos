package org.vaadin.tarek.combobox;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("ComboBoxBasicView")
@CssImport(value = "./styles/vaadin-combobox-overlay.css", themeFor = "vaadin-combo-box-overlay")
@CssImport(value = "./styles/vaadin-combo-box-item-styles.css", themeFor = "vaadin-combo-box-item")
public class ComboBoxBasicView extends VerticalLayout {

    public ComboBoxBasicView() {
        ComboBox<String> simple = new ComboBox<>();
        simple.setItems("Option one", "Option two", "Option three");
        simple.setValue("Option one");
        simple.setLabel("Simple ComboBox");

        ComboBox<String> wideOverlay = new ComboBox<>();
        wideOverlay.setItems("Option one", "Option two");
        wideOverlay.setPlaceholder("Placeholder");
        wideOverlay.getElement().setAttribute("theme", "wide-combobox-overlay");
        wideOverlay.setLabel("Wide-Overlay ComboBox");

        ComboBox<String> condensedList = new ComboBox<>();
        condensedList.setItems("Option one", "Option two");
        condensedList.getElement().setAttribute("theme", "custom-spacing");
        condensedList.setLabel("Condensed-List ComboBox");

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

        ComboBox<String> customItemColor = new ComboBox<>();
        customItemColor.setItems("Good #1", "Bad #1", "Good #2", "Good #3",
                "Bad #2");
        customItemColor.setRenderer(new ComponentRenderer<>(str -> {
            Span text = new Span(str);
            if (str.startsWith("Bad")) {
                text.getStyle().set("color", "red");
            } else if (str.startsWith("Good")) {
                text.getStyle().set("color", "green");
            }
            return text;
        }));
        customItemColor.setLabel("Custom item color");

        ComboBox<String> customBackground = new ComboBox<>();
        customBackground.setItems("Good #1", "Bad #1", "Good #2", "Good #3",
                "Bad #2");
        customBackground.setRenderer(new ComponentRenderer<>(str -> {
            Span text = new Span(str);
            if (str.startsWith("Bad")) {
                text.setClassName("bad");
            } else if (str.startsWith("Good")) {
                text.setClassName("good");
            }
            return text;
        }));
        customBackground.getElement().setAttribute("theme",
                "custom-item-background");
        customBackground.setLabel("Custom item background");
        customBackground.setValue("Good #2");

        ComboBox<String> hiddenSelectionIcon = new ComboBox<>();
        hiddenSelectionIcon.setItems("Good #1", "Bad #1", "Good #2", "Good #3",
                "Bad #2");
        hiddenSelectionIcon.getElement().setAttribute("theme",
                "hide-selection-button");
        hiddenSelectionIcon.setLabel("Hidden selection icon");

        ComboBox<String> customSelectionIcons = new ComboBox<>();
        customSelectionIcons.setItems("Option one", "Option two",
                "Option three");
        customSelectionIcons.setValue("Option one");
        customSelectionIcons.getElement().setAttribute("theme",
                "custom-selection-icons");
        customSelectionIcons.setLabel("Custom selection icons");

        add(simple, wideOverlay, condensedList, filteringComboBox,
                customItemColor, customBackground, hiddenSelectionIcon,
                customSelectionIcons);
    }
}
