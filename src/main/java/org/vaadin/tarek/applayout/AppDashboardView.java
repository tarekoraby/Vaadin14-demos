package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "AppDashboardView", layout = AppMainView.class)
public class AppDashboardView extends VerticalLayout {

    public AppDashboardView() {
        for (int i = 0; i < 50; i++) {
            add(new Span("Admin view content"));
        }

        add(new Button("scroll to top",
                e -> getElement().executeJs(
                        "document.querySelector('vaadin-app-layout').shadowRoot.querySelector('[content]').scrollTo(0, 0)")));
    }
}