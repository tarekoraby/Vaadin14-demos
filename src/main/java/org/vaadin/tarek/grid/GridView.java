package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridView")
public class GridView extends VerticalLayout {

    public GridView() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        // The Grid<>(Person.class) sorts the properties and in order to
        // reorder the properties we use the 'setColumns' method.
        grid.setColumns("firstName", "lastName", "age");

        // Remove and add columns manually
        grid.removeAllColumns();
        grid.addColumn(Person::getFirstName).setHeader("First Name");
        grid.addColumn(Person::getLastName).setHeader("Last Name");
        grid.addColumn(Person::getAge).setHeader("Age");

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
            return this.firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
