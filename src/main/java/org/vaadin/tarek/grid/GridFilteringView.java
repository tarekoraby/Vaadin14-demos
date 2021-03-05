package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridFilteringView")
public class GridFilteringView extends VerticalLayout {

    VerticalLayout filteredItemsLayout = new VerticalLayout();
    ListDataProvider<Person> dataProvider;

    public GridFilteringView() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Jack", "Woodward", 28));
        dataProvider = new ListDataProvider<>(personList);

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setDataProvider(dataProvider);

        IntegerField minAge = new IntegerField("Min age");
        minAge.setValue(0);
        minAge.setValueChangeMode(ValueChangeMode.EAGER);
        minAge.addValueChangeListener(e -> {
            filterGrid(e.getValue());
            List<Person> filteredList = new ArrayList<>();
            for (Person person : personList) {
                if (dataProvider.getFilter().test(person)) {
                    filteredList.add(person);
                }
            }
            updateFilteredItemsLayout(filteredList);
        });

        updateFilteredItemsLayout(personList);

        add(grid, minAge, filteredItemsLayout);
    }

    private void updateFilteredItemsLayout(List<Person> filteredList) {
        filteredItemsLayout.removeAll();
        filteredItemsLayout.add(new H3("Filtered items"));
        for (Person person : filteredList) {
            filteredItemsLayout.add(new Span(person.getFirstName() + " " + person.getLastName() + ": " + person.age));
        }
    }

    private void filterGrid(Integer minAge) {
        dataProvider.setFilter(person -> {
            if (minAge == null) {
                return true;
            }
            return person.getAge() > minAge;
        });
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
