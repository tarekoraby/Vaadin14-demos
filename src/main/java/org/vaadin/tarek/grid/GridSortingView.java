package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;

@Route("GridSortingView")
public class GridSortingView extends VerticalLayout {

    Grid<Person> grid;
    List<Person> personList;

    public GridSortingView() {
        personList = new ArrayList<>();

        personList.add(new Person("Aaron", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Aaron", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.removeAllColumns();

        grid.setSelectionMode(SelectionMode.NONE);

        grid.addColumn(Person::getFirstName, "First Name").setHeader("First Name");
        grid.addColumn(Person::getLastName, "Last Name").setHeader("Last Name");
        grid.addColumn(Person::getAge, "age").setHeader("Age");


        grid.addColumn(TemplateRenderer.<Person> of("<div>[[item.fn]]<br><small>[[item.ln]]</small></div>")

                .withProperty("fn", Person::getFirstName).withProperty("ln", Person::getLastName))
                .setHeader("Full name").setComparator(p -> p.getFirstName() + p.getLastName());

        Checkbox multiSort = new Checkbox("Multiple column sorting enabled");
        multiSort.addValueChangeListener(event -> grid.setMultiSort(event.getValue()));

        // you can set the sort order from server-side with the grid.sort method
        NativeButton invertAllSortings = new NativeButton("Invert all sort directions", event -> {
            List<GridSortOrder<Person>> newList = grid.getSortOrder().stream()
                    .map(order -> new GridSortOrder<>(order.getSorted(), order.getDirection().getOpposite()))
                    .collect(Collectors.toList());
            grid.sort(newList);
        });

        NativeButton resetAllSortings = new NativeButton("Reset all sortings", event -> grid.sort(null));
        add(grid, multiSort, invertAllSortings, resetAllSortings);
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
