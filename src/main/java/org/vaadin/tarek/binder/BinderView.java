package org.vaadin.tarek.binder;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("BinderView")
public class BinderView extends VerticalLayout {

    private final List<Person> personList;

    public BinderView() {
        personList = new ArrayList<>();

        personList.add(new Person("Peter", new Account(111)));
        personList.add(new Person("Aaron", new Account(222)));
        personList.add(new Person("Jack", new Account(333)));

        ComboBox<Person> comboBox = new ComboBox<>();
        comboBox.setItems(personList);
        comboBox.setItemLabelGenerator(Person::getName);

        Account accountBean = new Account(111);

        Binder<Account> binder = new Binder<>(Account.class);
        binder.bind(comboBox,
                acc -> getPerson(acc.getNumber()),
                (acc, person) -> {
                    if (person.getAccount() != null) {
                        acc.setNumber(person.getAccount().getNumber());
                    }
                });

        binder.setBean(accountBean);

        binder.addValueChangeListener(e -> Notification.show(
                "Name: " + getPerson(accountBean.getNumber()).getName() + ", Account number: "
                        + accountBean.getNumber()));

        Label label = new Label("Bean Value:");
        Span beanValue = new Span(Integer.toString(accountBean.getNumber()));
        binder.addValueChangeListener(e -> beanValue.setText(Integer.toString(accountBean.getNumber())));

        add(comboBox, label, beanValue);
    }

    private Person getPerson(int accNumber) {
        return personList.stream().filter(p -> p.getAccount().getNumber() == accNumber).findFirst().orElse(null);
    }

    public class Person {
        private String name;
        private Account account;

        public Person(String name, Account account) {
            super();
            this.name = name;
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }
    }

    public class Account {
        private int number;

        public Account(int number) {
            super();
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}