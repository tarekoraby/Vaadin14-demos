package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("GridContextMenuView")
public class GridContextMenuView extends Div {

    Grid<Person> grid;
    List<Person> personList;

    public GridContextMenuView() {
        personList = new ArrayList<>();

        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));

        grid = new Grid<>(Person.class);
        grid.setItems(personList);

        GridContextMenu<Person> contextMenu = grid.addContextMenu();

        Label message = new Label("-");

        contextMenu.addItem("menu item", e -> message.setText(
                "Clicked on item: " + e.getItem().get().getFirstName()));

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
