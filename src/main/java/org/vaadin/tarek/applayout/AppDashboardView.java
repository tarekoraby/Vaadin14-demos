package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = "AppDashboardView", layout = AppMainView.class)
public class AppDashboardView extends Div {

    public AppDashboardView() {
        add(new Span("Dashboard view content"));
    }
}