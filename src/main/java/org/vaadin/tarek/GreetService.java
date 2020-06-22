package org.vaadin.tarek;

public class GreetService {

    public String greet(String name) {
        if ((name == null) || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
}
