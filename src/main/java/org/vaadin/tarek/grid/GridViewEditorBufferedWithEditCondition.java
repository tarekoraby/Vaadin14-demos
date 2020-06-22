package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridViewEditorBufferedWithEditCondition")
public class GridViewEditorBufferedWithEditCondition extends VerticalLayout {

    public GridViewEditorBufferedWithEditCondition() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        Grid<Person> grid = new Grid<>();
        grid.setItems(personList);
        Grid.Column<Person> firstNameColumn = grid.addColumn(Person::getFirstName).setHeader("First Name");
        Grid.Column<Person> ageColumn = grid.addColumn(Person::getAge).setHeader("Age");

        Binder<Person> binder = new Binder<>(Person.class);
        Editor<Person> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        Div validationStatus = new Div();
        validationStatus.setId("validation");

        TextField firstNameField = new TextField();
        binder.forField(firstNameField)
        .withValidator(new StringLengthValidator("First name length must be between 3 and 50.", 3, 50))
        .withStatusLabel(validationStatus).bind("firstName");
        firstNameColumn.setEditorComponent(firstNameField);

        TextField ageField = new TextField();
        binder.forField(ageField).withConverter(new StringToIntegerConverter("Age must be a number."))
        .withStatusLabel(validationStatus).bind("age");
        ageColumn.setEditorComponent(ageField);

        Map<Button, Boolean> editButtons = new HashMap<>();

        Grid.Column<Person> editorColumn = grid.addComponentColumn(person -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                editor.editItem(person);
                firstNameField.focus();
            });
            if (editor.isOpen() || (person.getAge() < 20)) {
                edit.setEnabled(false);
            }
            editButtons.put(edit, person.getAge() > 20);
            return edit;
        });

        editor.addOpenListener(
                e -> editButtons.forEach((button, isValid) -> button.setEnabled(!editor.isOpen() && isValid)));
        editor.addCloseListener(
                e -> editButtons.forEach((button, isValid) -> button.setEnabled(!editor.isOpen() && isValid)));

        Button save = new Button("Save", e -> editor.save());
        save.addClassName("save");

        Button cancel = new Button("Cancel", e -> editor.cancel());
        cancel.addClassName("cancel");

        // Add a keypress listener that listens for an escape key up event.
        // Note! some browsers return key as Escape and some as Esc
        grid.getElement().addEventListener("keyup", event -> editor.cancel())
        .setFilter("event.key === 'Escape' || event.key === 'Esc'");

        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);

        add(grid);
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
