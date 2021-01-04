package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.GridMultiSelectionModel.SelectAllCheckboxVisibility;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridMultiSelectView")
public class GridMultiSelectView extends VerticalLayout {

    public GridMultiSelectView() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.setSelectionMode(SelectionMode.MULTI);

        GridMultiSelectionModel<Person> gridMultiSelectionModel = (GridMultiSelectionModel<Person>) grid
                .getSelectionModel();
        Button deselectAll = new Button("Deselect all", e -> gridMultiSelectionModel.deselectAll());
        Button selectAll = new Button("Select all", e -> gridMultiSelectionModel.selectAll());

        Checkbox selectAllCheckboxVisibility = new Checkbox("Select-all checkbox visible",
                e -> {
                    if (e.getValue()) {
                        gridMultiSelectionModel.setSelectAllCheckboxVisibility(SelectAllCheckboxVisibility.VISIBLE);
                    } else {
                        gridMultiSelectionModel.setSelectAllCheckboxVisibility(SelectAllCheckboxVisibility.HIDDEN);
                    }
                });
        selectAllCheckboxVisibility.setValue(true);

        add(grid, deselectAll, selectAll, selectAllCheckboxVisibility);
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
