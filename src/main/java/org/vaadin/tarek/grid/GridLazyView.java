package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("GridLazyView")
public class GridLazyView extends VerticalLayout {

    public GridLazyView() {
        Grid<Person> grid = new Grid<>(Person.class);
        PersonService personService = new PersonService();

        /*
         * This Data Provider doesn't load all items into the memory right away.
         * Grid will request only the data that should be shown in its current
         * view "window". The Data Provider will use callbacks to load only a
         * portion of the data.
         */
        DataProvider<Person, Void> provider = DataProvider
                .fromCallbacks(query -> personService.fetchPersons(query.getOffset(),
                        query.getLimit()),
                        query -> personService.getPersonCount());
        grid.setDataProvider(provider);

        grid.setColumns("id", "firstName", "lastName");

        add(grid);
    }

    class PersonService {
        List<Person> personList;

        public Stream<Person> fetchPersons(int offset, int limit) {
            delayInSeconds(3);
            ensureTestData();
            return personList.stream().skip(offset).limit(limit);
        }

        public int getPersonCount() {
            ensureTestData();
            return personList.size();
        }

        private void delayInSeconds(int seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void ensureTestData() {
            if (personList == null) {
                personList = createPersonList(1000);
            }
        }

        private List<Person> createPersonList(final int len) {
            final List<Person> newPersonList = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                int nameLen = ThreadLocalRandom.current().nextInt(3, 10 + 1);
                final Person newPerson = new Person(
                        RandomStringUtils.randomAlphabetic(nameLen),
                        RandomStringUtils.randomAlphabetic(nameLen),
                        i);
                newPersonList.add(newPerson);
            }
            return newPersonList;
        }

    }


    public class Person {
        private String firstName;
        private String lastName;
        private int id;

        public Person(String firstName, String lastName, int id) {
            setFirstName(firstName);
            setLastName(lastName);
            setId(id);
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
