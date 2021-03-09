package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@CssImport(value = "./styles/vaadin-grid-styles.css", themeFor = "vaadin-grid")
@Route("GridMultiLineView")
public class GridMultiLineView extends VerticalLayout {

    public GridMultiLineView() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Some\nmultiline\ntext", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.addClassName("multiline");

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
