package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = "AppAdminView", layout = AppMainView.class)
public class AppAdminView extends Div {
    public AppAdminView() {
        add(new Span("Admin view content"));
    }
}
