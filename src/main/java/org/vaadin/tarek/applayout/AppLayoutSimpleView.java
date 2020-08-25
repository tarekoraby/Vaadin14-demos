package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("AppLayoutSimpleView")
public class AppLayoutSimpleView extends AppLayout {

    public AppLayoutSimpleView() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(new Tab(new RouterLink("tab 1", AppLayoutSimpleView.class)));
        tabs.add(new Tab(new RouterLink("tab 2", AppLayoutSimpleView.class)));
        tabs.add(new Tab(new RouterLink("tab 3", AppLayoutSimpleView.class)));
        addToDrawer(tabs);

        addToNavbar(new Button("navbar 1"));

        setContent(new Button("content"));
    }
}