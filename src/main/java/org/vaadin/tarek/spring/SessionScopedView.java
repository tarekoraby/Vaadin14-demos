package org.vaadin.tarek.spring;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("SessionScopedView")
public class SessionScopedView extends VerticalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

    private final SessionScopedPojo sessionScoped;

    public SessionScopedView(@Autowired SessionScopedPojo sessionScoped) {
        this.sessionScoped = sessionScoped;
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        sessionScoped.getParameters().add(parameter);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        for (String parameter : sessionScoped.getParameters()) {
            add(new Span(parameter));
        }
    }
}