package com.example.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;


import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudEditorPosition;
import com.vaadin.flow.component.crud.CrudFilter;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;

@Route("CrudView")
public class CrudView extends VerticalLayout {

    public CrudView() {
        Crud<Person> crud = new Crud<>(Person.class, createGrid(), createPersonEditor());
        PersonDataProvider dataProvider = new PersonDataProvider();

        crud.setDataProvider(dataProvider);
        crud.addSaveListener(e -> {
            try {
                dataProvider.persist(e.getItem());
            } catch (Exception e1) {
            } 
        });
        crud.addDeleteListener(e -> dataProvider.delete(e.getItem()));
        crud.setEditorPosition(CrudEditorPosition.ASIDE);
        crud.getGrid().addItemClickListener(e -> {
            if (e.getItem().getFirstName() != null && !e.getItem().getFirstName().isEmpty())
                crud.edit(e.getItem(), Crud.EditMode.EXISTING_ITEM);
        });

        add(crud);
    }

    private Grid<Person> createGrid() {
        Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getFirstName).setHeader("firstName");
        grid.addColumn(Person::getLastName).setHeader("lastName");
        return grid;
    }

    private CrudEditor<Person> createPersonEditor() {
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        FormLayout form = new FormLayout(firstName, lastName);
        Binder<Person> binder = new Binder<>(Person.class);
        binder.bind(firstName, Person::getFirstName, Person::setFirstName);
        binder.bind(lastName, Person::getLastName, Person::setLastName);
        return new BinderCrudEditor<>(binder, form);
    }

    // Person Bean
    public static class Person implements Cloneable {
        private Integer id;

        private String firstName;

        private String lastName;

        /**
         * No-arg constructor required by Crud to be able to instantiate a new
         * bean when the new item button is clicked.
         */
        public Person() {
        }

        public Person(Integer id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public Person clone() {
            try {
                return (Person) super.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }

    // Person data provider
    public static class PersonDataProvider extends AbstractBackEndDataProvider<Person, CrudFilter> {

        // A real app should hook up something like JPA
        final List<Person> DATABASE = createPersonList(20);

        private Consumer<Long> sizeChangeListener;

        @Override
        protected Stream<Person> fetchFromBackEnd(Query<Person, CrudFilter> query) {
            int offset = query.getOffset();
            int limit = query.getLimit();

            Stream<Person> stream = DATABASE.stream();

            if (query.getFilter().isPresent()) {
                stream = stream.filter(predicate(query.getFilter().get())).sorted(comparator(query.getFilter().get()));
            }

            return stream.skip(offset).limit(limit);
        }

        private List<Person> createPersonList(int len) {
            List<Person> personList = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                Person newPerson = new Person();

                newPerson.setId(i);

                int nameLen = ThreadLocalRandom.current().nextInt(3, 10 + 1);
                newPerson.setFirstName(RandomStringUtils.randomAlphabetic(nameLen));

                nameLen = ThreadLocalRandom.current().nextInt(3, 10 + 1);
                newPerson.setLastName(RandomStringUtils.randomAlphabetic(nameLen));

                personList.add(newPerson);
            }
            return personList;
        }

        @Override
        protected int sizeInBackEnd(Query<Person, CrudFilter> query) {
            // For RDBMS just execute a SELECT COUNT(*) ... WHERE query
            long count = fetchFromBackEnd(query).count();

            if (sizeChangeListener != null) {
                sizeChangeListener.accept(count);
            }

            return (int) count;
        }

        void setSizeChangeListener(Consumer<Long> listener) {
            sizeChangeListener = listener;
        }

        private static Predicate<Person> predicate(CrudFilter filter) {
            // For RDBMS just generate a WHERE clause
            return filter.getConstraints().entrySet().stream().map(constraint -> (Predicate<Person>) person -> {
                try {
                    Object value = valueOf(constraint.getKey(), person);
                    return value != null && value.toString().toLowerCase().contains(constraint.getValue().toLowerCase());
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }).reduce(Predicate::and).orElse(e -> true);
        }

        private static Comparator<Person> comparator(CrudFilter filter) {
            // For RDBMS just generate an ORDER BY clause
            return filter.getSortOrders().entrySet().stream().map(sortClause -> {
                try {
                    Comparator<Person> comparator = Comparator.comparing(person -> (Comparable) valueOf(sortClause.getKey(), person));

                    if (sortClause.getValue() == SortDirection.DESCENDING) {
                        comparator = comparator.reversed();
                    }

                    return comparator;
                } catch (Exception ex) {
                    return (Comparator<Person>) (o1, o2) -> 0;
                }
            }).reduce(Comparator::thenComparing).orElse((o1, o2) -> 0);
        }

        private static Object valueOf(String fieldName, Person person) {
            try {
                Field field = Person.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(person);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        void persist(Person item) throws Exception {
            if (item.getId() == null) {
                item.setId(DATABASE.stream().map(Person::getId).max(Comparator.naturalOrder()).orElse(0) + 1);
            }

            final Optional<Person> existingItem = find(item.getId());
            if (existingItem.isPresent()) {
                int position = DATABASE.indexOf(existingItem.get());
                DATABASE.remove(existingItem.get());
                DATABASE.add(position, item);
            } else {
                DATABASE.add(item);
            }

            throw new Exception();
        }

        Optional<Person> find(Integer id) {
            return DATABASE.stream().filter(entity -> entity.getId().equals(id)).findFirst();
        }

        void delete(Person item) {
            DATABASE.removeIf(entity -> entity.getId().equals(item.getId()));
        }
    }
}