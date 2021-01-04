package org.vaadin.tarek.responsivelayout;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ResponsiveLayout")
public class ResponsiveLayout extends VerticalLayout {

    public ResponsiveLayout() {
        add(new Text("Welcome to a Responsive Layout."));

        FormLayout nameLayout = new FormLayout();

        TextField titleField = new TextField();
        titleField.setLabel("Title");
        titleField.setPlaceholder("Sir");
        TextField firstNameField = new TextField();
        firstNameField.setLabel("First name");
        firstNameField.setPlaceholder("John");
        TextField lastNameField = new TextField();
        lastNameField.setLabel("Last name");
        lastNameField.setPlaceholder("Doe");

        nameLayout.add(titleField, firstNameField, lastNameField);

        nameLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2),
                new FormLayout.ResponsiveStep("40em", 3));
        add(nameLayout);

        FormLayout layoutWithFormItems = new FormLayout();

        TextField firstName = new TextField();
        firstName.setPlaceholder("John");
        layoutWithFormItems.addFormItem(firstName, "First name");

        TextField lastName = new TextField();
        lastName.setPlaceholder("Doe");
        layoutWithFormItems.addFormItem(lastName, "Last name");
        add(layoutWithFormItems);
    }
}
