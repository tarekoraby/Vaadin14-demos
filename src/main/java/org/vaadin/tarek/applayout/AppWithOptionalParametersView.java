package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route(value = "AppWithOptionalParametersView", layout = AppMainView.class)
public class AppWithOptionalParametersView
extends Div
implements HasUrlParameter<String> {

    H1 heading;

    public AppWithOptionalParametersView() {
        this.heading = new H1("Default view content (without parameters)");
        add(this.heading);
        for (int i = 0; i < 50; i++) {
            add(new Paragraph("Admin view content"));
        }
    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {
        if (parameter != null) {
            this.heading.setText(parameter);
        }
    }

}