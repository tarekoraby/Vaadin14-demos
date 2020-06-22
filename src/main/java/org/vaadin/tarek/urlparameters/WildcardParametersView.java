package org.vaadin.tarek.urlparameters;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

/**
 * The main view contains a button and a click listener.
 */
@Route("WildcardParametersView")
public class WildcardParametersView extends VerticalLayout implements HasUrlParameter<String> {

    Span span;

    public WildcardParametersView() {
        span = new Span();
        add(new H3("Parameters:"), span);
    }

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        if (parameter.isEmpty()) {
            span.setText("Welcome anonymous.");
        } else {
            span.setText(String.format("Welcome %s.", parameter));
        }
    }
}
