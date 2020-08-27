package org.vaadin.tarek.binder;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("BinderDynamicValidationView")
public class BinderDynamicValidationView extends VerticalLayout {

    private boolean requirednessEnabled;

    public BinderDynamicValidationView() {
        Binder<Person> binder = new Binder<>(Person.class);

        TextField firstNameField = new TextField("First name");
        firstNameField.setValueChangeMode(ValueChangeMode.EAGER);
        binder.forField(firstNameField)
                .withValidator(this::isValid,
                        "Name cannot be empty")
                .bind(Person::getFirstName, Person::setFirstName);

        Person person = new Person("");
        binder.setBean(person);

        Button toggleRequired = new Button("Toggle-requiredness", e -> {
            requirednessEnabled = !requirednessEnabled;
            binder.validate();
        });

        add(firstNameField, toggleRequired);
    }

    public boolean isValid(String name) {
        return !requirednessEnabled || !name.isEmpty();
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
