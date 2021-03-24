package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("GridLazyFilteringView")
public class GridLazyFilteringView extends VerticalLayout {

    public GridLazyFilteringView() {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setColumns("firstName", "lastName", "age");

        PersonService personService = new PersonService();
        DataProvider<Person, PersonFilter> dataProvider = DataProvider.fromFilteringCallbacks(
                query -> personService.fetchPersons(query.getOffset(), query.getLimit(),
                        query.getFilter().orElse(null)),
                query -> personService.getPersonCount(query.getFilter().orElse(null)));
        ConfigurableFilterDataProvider<Person, Void, PersonFilter> wrapper = dataProvider
                .withConfigurableFilter();
        grid.setDataProvider(wrapper);


        IntegerField minAge = new IntegerField("Min age");
        minAge.setValue(0);
        minAge.setValueChangeMode(ValueChangeMode.EAGER);
        minAge.addValueChangeListener(e -> {
            wrapper.setFilter(new PersonFilter(e.getValue()));
        });

        add(grid, minAge);
    }

    class PersonService {
        List<Person> personList;

        public Stream<Person> fetchPersons(int offset, int limit, PersonFilter personFilter) {
            delayInSeconds(1);
            ensureTestData();
            if (personFilter == null) {
                return personList.stream().skip(offset).limit(limit);
            } else {
                return personList.stream().filter(p -> p.age > personFilter.age).skip(offset).limit(limit);
            }
        }

        public int getPersonCount(PersonFilter personFilter) {
            ensureTestData();
            if (personFilter == null) {
                return personList.size();
            } else {
                return (int) personList.stream().filter(p -> p.age > personFilter.age).count();
            }
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
                int nameLen = ThreadLocalRandom.current().nextInt(1, 3);
                final Person newPerson = new Person(RandomStringUtils.randomAlphabetic(nameLen).toLowerCase(),
                        RandomStringUtils.randomAlphabetic(nameLen).toLowerCase(),
                        ThreadLocalRandom.current().nextInt(1, 99));
                newPersonList.add(newPerson);
            }
            return newPersonList;
        }

    }

    public class PersonFilter {
        private final Integer age;

        public PersonFilter(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

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
