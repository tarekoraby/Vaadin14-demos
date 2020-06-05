package com.example.test.gridpro;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("gridproview")
public class GridProView extends VerticalLayout {

    public GridProView() {
        GridPro<Person> grid = new GridPro<>();
        grid.setItems(createItems());

        /*
         * Grid Pro is an extension of the Grid and provides all the same
         * functionality on top of basic one. It is possible to use Grid's API
         * in Grid Pro.
         */
        grid.addColumn(Person::getFirstName).setHeader("First Name");
        grid.addColumn(Person::getLastName).setHeader("Last Name");

        /*
         * Lambda provided as a parameter for .text() method is a callback
         * function that will be called when item is changed.
         */
        grid.addEditColumn(Person::getEmail).text(Person::setEmail)
                .setHeader("Email (editable)");

        // Allow cells' editing with a single click
        grid.addAttachListener(a -> {
            grid.getElement().executeJs(
                    "this.addEventListener('click', function(e) {this._enterEditFromEvent(e)} )");
        });

        add(grid);
    }

    private Collection<Person> createItems() {
        Collection<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", "LK@vaadin.com"));
        personList.add(new Person("Peter", "Buchanan", "PB@vaadin.com"));
        personList.add(new Person("Samuel", "Lee", "SL@vaadin.com"));
        personList.add(new Person("Anton", "Ross", "AR@vaadin.com"));
        personList.add(new Person("Aaron", "Atkinson", "AA@vaadin.com"));
        personList.add(new Person("Jack", "Woodward", "AW@vaadin.com"));

        return personList;
    }

    class Person {
        private String firstName;
        private String lastName;
        private String email;

        public Person(String firstName, String lastName, String email) {
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
