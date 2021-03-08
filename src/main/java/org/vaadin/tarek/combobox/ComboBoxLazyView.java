package org.vaadin.tarek.combobox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ComboBoxLazyView")
public class ComboBoxLazyView extends VerticalLayout {

    public ComboBoxLazyView() {
        ComboBox<Person> combo = new ComboBox<>();
        combo.setItemLabelGenerator(p -> p.getFirstName() + " " + p.getLastName());

        PersonService personService = new PersonService();
        combo.setDataProvider((filter, offset, limit) -> personService.fetchPersons(filter, offset, limit),
                filter -> personService.getPersonCount(filter));

        add(combo);
    }

    class PersonService {
        List<Person> personList;

        public Stream<Person> fetchPersons(String filter, int offset, int limit) {
            delayInSeconds(1);
            ensureTestData();
            Stream<Person> filtered = personList.stream().filter(p -> {
                String filterLow = filter.toLowerCase();
                return p.getFirstName().toLowerCase().contains(filterLow)
                        || p.getLastName().toLowerCase().contains(filterLow);
            });
            return filtered.skip(offset).limit(limit);
        }

        public int getPersonCount(String filter) {
            ensureTestData();
            Stream<Person> filtered = personList.stream().filter(p -> {
                String filterLow = filter.toLowerCase();
                return p.getFirstName().toLowerCase().contains(filterLow)
                        || p.getLastName().toLowerCase().contains(filterLow);
            });
            return (int) filtered.count();
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
                final Person newPerson = new Person(RandomStringUtils.randomAlphabetic(nameLen),
                        RandomStringUtils.randomAlphabetic(nameLen), i);
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
