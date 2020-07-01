package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route(value = "AppWithOptionalParametersView", layout = AppMainView.class)
public class AppWithOptionalParametersView extends Div implements HasUrlParameter<String> {

    Span span;

    public AppWithOptionalParametersView() {
        this.span = new Span("Default view content (without parameters)");
        add(this.span);
    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {
        if (parameter != null) {
            this.span.setText(parameter);
        }
    }
}