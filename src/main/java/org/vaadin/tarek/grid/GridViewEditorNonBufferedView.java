package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridViewEditorNonBufferedView")
public class GridViewEditorNonBufferedView extends VerticalLayout {

    Span message = new Span();

    public GridViewEditorNonBufferedView() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        Grid<Person> grid = new Grid<>();
        grid.setItems(personList);
        Grid.Column<Person> nameColumn = grid.addColumn(Person::getFirstName).setHeader("First Name");
        Grid.Column<Person> ageColumn = grid.addColumn(Person::getAge).setHeader("Age");

        Binder<Person> binder = new Binder<>(Person.class);
        grid.getEditor().setBinder(binder);

        TextField firstNameField = new TextField();
        TextField ageField = new TextField();
        // Close the editor in case of backward between components
        firstNameField.getElement().addEventListener("keydown", event -> grid.getEditor().cancel())
                .setFilter("event.key === 'Tab' && event.shiftKey");

        binder.forField(firstNameField)
                .withValidator(new StringLengthValidator("First name length must be between 3 and 50.", 3, 50))
                .bind("firstName");
        nameColumn.setEditorComponent(firstNameField);

        ageField.getElement().addEventListener("keydown", event -> grid.getEditor().cancel())
                .setFilter("event.key === 'Tab'");
        binder.forField(ageField).withConverter(new StringToIntegerConverter("Age must be a number.")).bind("age");
        ageColumn.setEditorComponent(ageField);

        grid.addItemClickListener(event -> {
            grid.getEditor().editItem(event.getItem());
            firstNameField.focus();
        });

        grid.getEditor().addCloseListener(event -> {
            if (binder.getBean() != null) {
                message.setText(binder.getBean().getFirstName() + ", " + binder.getBean().getAge());
            }
        });

        add(grid, message);
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
