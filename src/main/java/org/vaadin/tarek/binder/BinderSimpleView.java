package org.vaadin.tarek.binder;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("BinderSimpleView")
public class BinderSimpleView extends VerticalLayout {

    private boolean requirednessEnabled;

    public BinderSimpleView() {
        Binder<Person> binder = new Binder<>(Person.class);

        TextField firstNameField = new TextField("First name");
        firstNameField.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameField.setRequiredIndicatorVisible(true);

        binder.forField(firstNameField)
                .bind(Person::getFirstName, Person::setFirstName);

        Person person = new Person("Lucas", "Kane", 68);
        binder.setBean(person);

        Label label = new Label("Bean Value:");
        Span beanValue = new Span(person.getFirstName());
        binder.addValueChangeListener(
                e -> beanValue.setText(person.getFirstName()));

        add(firstNameField, label, beanValue);
    }

    public class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            setFirstName(firstName);
            setLastName(lastName);
            setAge(age);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
