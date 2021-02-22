package org.vaadin.tarek.binder;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("BinderValidationExceptionView")
public class BinderValidationExceptionView extends VerticalLayout {

    public BinderValidationExceptionView() {
        Binder<Person> binder = new Binder<>(Person.class);

        TextField firstNameField = new TextField("First name");
        firstNameField.setValueChangeMode(ValueChangeMode.EAGER);
        binder.forField(firstNameField).withValidator(name -> !name.isEmpty(), "Name cannot be empty")
                .bind(Person::getFirstName, Person::setFirstName);

        firstNameField.setVisible(false);
        firstNameField.setId("firstNameField");

        Person person = new Person("");

        binder.readBean(person);

        Button saveButton = new Button("Save", event -> {
            try {
                binder.writeBean(person);
            } catch (ValidationException e) {
                processValidationException(e);
            }
        });

        add(firstNameField, saveButton);

    }

    private void processValidationException(ValidationException e) {
        List<BindingValidationStatus<?>> errors = e.getFieldValidationErrors();

        for (BindingValidationStatus<?> bindingValidationStatus : errors) {
            Component field = (Component) bindingValidationStatus.getField();
            add(new Span("Error message: " + bindingValidationStatus.getMessage().orElse("No message")));
            add(new Span("Field id: " + field.getId().orElse("No id")));
        }

    }

    public class Person {
        private String firstName;

        public Person(String firstName) {
            setFirstName(firstName);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

}
