package org.vaadin.tarek.draganddrop;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("DropToGridView")
public class DropToGridView extends VerticalLayout {

    public DropToGridView() {

        Grid<?> grid = createGrid();
        grid.removeAllColumns();

        Span colFirstName = new Span();
        colFirstName.setText("First name");
        DragSource.create(colFirstName);

        Span colLasttName = new Span();
        colLasttName.setText("Last name");
        DragSource.create(colLasttName);

        Span colAge = new Span();
        colAge.setText("Age");
        DragSource.create(colAge);

        DropTarget<Grid<?>> dropTarget = DropTarget.create(grid);

        dropTarget.addDropListener(event -> {
            event.getDragSourceComponent().ifPresent(component -> {
                if (component.equals(colFirstName)) {
                    grid.addColumn("firstName");
                } else if (component.equals(colLasttName)) {
                    grid.addColumn("lastName");
                } else if (component.equals(colAge)) {
                    grid.addColumn("age");
                }
            });
        });

        add(grid, colFirstName, colLasttName, colAge);

    }

    Grid<?> createGrid() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);

        return grid;

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
