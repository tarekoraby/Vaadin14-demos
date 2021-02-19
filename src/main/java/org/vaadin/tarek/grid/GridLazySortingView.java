package org.vaadin.tarek.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;

@Route("GridLazySortingView")
public class GridLazySortingView extends VerticalLayout {


    public GridLazySortingView() {
        Grid<Person> grid = new Grid<>(Person.class);

        grid.setColumns("firstName", "lastName", "age");
        grid.setMultiSort(true);

        grid.sort(Arrays.asList(new GridSortOrder<>(grid.getColumnByKey("firstName"), SortDirection.ASCENDING),
                new GridSortOrder<>(grid.getColumnByKey("lastName"), SortDirection.ASCENDING),
                new GridSortOrder<>(grid.getColumnByKey("age"), SortDirection.ASCENDING)));
        PersonService personService = new PersonService();
        DataProvider<Person, Void> provider = DataProvider.fromCallbacks(
                query -> personService.fetchPersons(query.getOffset(), query.getLimit(), query.getSortOrders()),
                query -> personService.getPersonCount());
        grid.setDataProvider(provider);

        add(grid);

    }

    class PersonService {
        List<Person> personList;

        public Stream<Person> fetchPersons(int offset, int limit, List<QuerySortOrder> sortOrders) {
            delayInSeconds(1);
            ensureTestData();
            List<Person> sortedPersonList = sortList(sortOrders);
            return sortedPersonList.stream().skip(offset).limit(limit);
        }

        private List<Person> sortList(List<QuerySortOrder> sortOrders) {
            if ((sortOrders == null) || sortOrders.isEmpty()) {
                return personList;
            }
            List<Person> sortedPersonList = new ArrayList<>(personList);
            Collections.sort(sortedPersonList, (o1, o2) -> {
                for (QuerySortOrder querySortOrder : sortOrders) {
                    String sortingEntity = querySortOrder.getSorted();
                    int direction = querySortOrder.getDirection().equals(SortDirection.ASCENDING) ? 1 : -1;
                    if (sortingEntity.equals("firstName") && !(o1.getFirstName().equals(o2.getFirstName()))) {
                        return o1.getFirstName().compareTo(o2.getFirstName()) * direction;
                    } else if (sortingEntity.equals("lastName") && !(o1.getLastName().equals(o2.getLastName()))) {
                        return o1.getLastName().compareTo(o2.getLastName()) * direction;
                    } else if (sortingEntity.equals("age") && (o1.getAge() != o2.getAge())) {
                        return (o1.getAge() > o2.getAge() ? 1 : -1) * direction;
                    }
                }
                return 0;
            });
            return sortedPersonList;
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
                int nameLen = ThreadLocalRandom.current().nextInt(1, 3);
                final Person newPerson = new Person(RandomStringUtils.randomAlphabetic(nameLen).toLowerCase(),
                        RandomStringUtils.randomAlphabetic(nameLen).toLowerCase(), i / 100);
                newPersonList.add(newPerson);
            }
            return newPersonList;
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
