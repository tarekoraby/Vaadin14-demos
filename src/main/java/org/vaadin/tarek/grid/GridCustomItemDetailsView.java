package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.tarek.grid.GridItemDetailsView.Person;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;

@Route("GridCustomItemDetailsView")
public class GridCustomItemDetailsView extends VerticalLayout {

    Grid<Person> grid;
    List<Person> personList;

    public GridCustomItemDetailsView() {
        personList = new ArrayList<>();

        personList.add(new Person("Peter", "Buchanan"));
        personList.add(new Person("Samuel", "Lee"));
        personList.add(new Person("Anton", "Ross"));
        personList.add(new Person("Aaron", "Atkinson"));
        personList.add(new Person("Jack", "Woodward"));

        grid = new Grid<>(Person.class);
        grid.setItems(personList);

        grid.removeAllColumns();

        grid.addColumn(Person::getFirstName).setHeader("First Name").setKey("FirstName");
        grid.addColumn(Person::getLastName).setHeader("Last Name").setKey("LastName");

        grid.setSelectionMode(Grid.SelectionMode.NONE);

        // Disable the default way of opening item details:
        grid.setDetailsVisibleOnClick(false);

        grid.addItemClickListener(event -> {
            Person item = event.getItem();
            String colKey = event.getColumn().getKey();

            if (colKey.equals("FirstName")) {
                setItemsDetailsRenderer(Person::getFirstName);
                grid.setDetailsVisible(item, !grid.isDetailsVisible(item));
            } else {
                setItemsDetailsRenderer(Person::getLastName);
                grid.setDetailsVisible(item, !grid.isDetailsVisible(item));
            }
        });

        add(grid);
    }

    private void setItemsDetailsRenderer(ValueProvider<Person, ?> provider) {
        grid.setItemDetailsRenderer(TemplateRenderer
                .<Person> of(
                        "<div on-click='handleClick'>" + "<div>Clicked on: <b>[[item.property]]!</b></div>" + "</div>")
                .withProperty("property", provider)
                // Clicking the inside the template will also close the details.
                .withEventHandler("handleClick", person -> {
                    grid.setDetailsVisible(person, false);
                }));
    }

    public class Person {
        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            setFirstName(firstName);
            setLastName(lastName);
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
    }
}