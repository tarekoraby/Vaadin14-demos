package org.vaadin.tarek.listbox;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ListBoxView")
@CssImport(value = "./styles/vaadin-list-box-styles.css", themeFor = "vaadin-list-box")
public class ListBoxView extends VerticalLayout {

    public ListBoxView() {
        ListBox<String> listBox = new ListBox<>();
        listBox.setItems("Option one", "Option two", "Option three");
        listBox.setValue("Option one");

        ListBox<String> styledListBox = new ListBox<>();
        styledListBox.setItems("Option one", "Option two", "Option three");
        styledListBox.setValue("Option one");
        styledListBox.getElement().setAttribute("theme", "styledListBox");

        add(listBox, new Html("<hr>"), styledListBox);
    }
}