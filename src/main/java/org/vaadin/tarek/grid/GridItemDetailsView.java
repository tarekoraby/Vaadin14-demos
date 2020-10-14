package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;

@Route("GridItemDetailsView")
public class GridItemDetailsView extends VerticalLayout {

    Grid<Person> grid;
    List<Person> personList;

    public GridItemDetailsView() {
        personList = new ArrayList<>();

        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));

        grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.removeAllColumns();

        grid.addColumn(Person::getFirstName).setHeader("First Name");
        grid.addColumn(Person::getLastName).setHeader("Last Name");
        grid.addColumn(Person::getAge).setHeader("Age");

        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setItemDetailsRenderer(TemplateRenderer.<Person> of(
                "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;' on-click='handleClick'>"
                        + "<div>Hi! My name is <b>[[item.firstName]]!</b></div>"
                        + "</div>")
                .withProperty("firstName", Person::getFirstName)
                // Clicking the inside the template will also close the details
                // That is, in addition to clicking the Details button (see
                // below)
                .withEventHandler("handleClick", person -> {
                    grid.setDetailsVisible(person, false);
                }));

        // Disable the default way of opening item details:
        grid.setDetailsVisibleOnClick(false);

        grid.addColumn(new NativeButtonRenderer<>("Details", item -> grid
                .setDetailsVisible(item, !grid.isDetailsVisible(item))));

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
