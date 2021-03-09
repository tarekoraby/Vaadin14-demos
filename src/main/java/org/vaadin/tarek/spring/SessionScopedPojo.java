package org.vaadin.tarek.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class SessionScopedPojo {
    private List<String> parameters;

    public SessionScopedPojo() {
        parameters = new ArrayList<>();
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

}
