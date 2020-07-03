package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "AppAdminView", layout = AppMainView.class)
public class AppAdminView extends VerticalLayout {
    public AppAdminView() {
        for (int i = 0; i < 50; i++) {
            add(new Span("Admin view content"));
        }
    }
}
