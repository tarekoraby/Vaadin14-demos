package org.vaadin.tarek.combobox;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("ComboBoxWithRendererView")
public class ComboBoxWithRendererView extends VerticalLayout {

    public ComboBoxWithRendererView() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Peter", "Buchanan", 38));
        personList.add(new Person("Aaron", "Atkinson", 18));
        personList.add(new Person("Samuel", "Lee", 53));
        personList.add(new Person("Anton", "Ross", 37));
        personList.add(new Person("Lucas", "Kane", 68));
        personList.add(new Person("Jack", "Woodward", 28));

        ComboBox<Person> comboBox = new ComboBox<>();
        comboBox.setLabel("Label");
        comboBox.setWidthFull();

        comboBox.setItems(personList);

        comboBox.setRenderer(new ComponentRenderer<>(person -> {
            String firstName = person.getFirstName();
            if (person.getAge() > 60) {
                return new H1(firstName + " is over 60");
            } else if (person.getAge() > 30) {
                return new H2(firstName + " is over 30");
            } else {
                return new H3(firstName + " is under 30");
            }
        }));

        add(comboBox);
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
