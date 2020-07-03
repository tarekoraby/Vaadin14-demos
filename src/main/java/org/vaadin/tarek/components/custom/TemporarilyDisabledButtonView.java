package org.vaadin.tarek.components.custom;

import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("TemporarilyDisabledButtonView")
public class TemporarilyDisabledButtonView extends VerticalLayout {

    public TemporarilyDisabledButtonView() {
        List<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", 1543),
                new Person("Galileo Galilei", 1564),
                new Person("Johannes Kepler", 1571));

        // Create a grid bound to the list
        Grid<Person> grid = new Grid<>();
        grid.setItems(people);
        grid.addColumn(Person::getName).setHeader("Name");
        grid.addColumn(Person::getYearOfBirth).setHeader("Year of birth");

        TemporarilyDisabledButton tempButton = new TemporarilyDisabledButton(
                "Temporarily disabled button", "Processing", 5000);

        Button normalButton = new Button(
                "Normal button with setDisableOnClick(true)");
        normalButton.addClickListener(e -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            normalButton.setEnabled(true);
        });
        normalButton.setDisableOnClick(true);

        Button button = new Button("Change first row name");
        button.addClickListener(e -> {
            people.get(0).setName("A different name");
            grid.getDataProvider().refreshAll();
        });

        add(grid, tempButton, normalButton, button);
    }

    class Person {
        String name;
        Integer yearofbirth;

        public Person(String name, Integer yearofbirth) {
            this.name = name;
            this.yearofbirth = yearofbirth;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getYearOfBirth() {
            return this.yearofbirth;
        }

        public void setYearOfBirth(Integer yearofbirth) {
            this.yearofbirth = yearofbirth;
        }
    }
}
