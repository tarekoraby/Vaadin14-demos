package org.vaadin.tarek.applayout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("AppLayoutSimpleView")
@CssImport(value = "./styles/vaadin-app-layout-styles.css", themeFor = "vaadin-app-layout")
public class AppLayoutSimpleView extends AppLayout {

    public AppLayoutSimpleView() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(new Tab(new RouterLink("tab 1", AppLayoutSimpleView.class)));
        tabs.add(new Tab(new RouterLink("tab 2", AppLayoutSimpleView.class)));
        tabs.add(new Tab(new RouterLink("tab 3", AppLayoutSimpleView.class)));
        addToDrawer(tabs);

        addToNavbar(new Anchor("https://vaadin.com", "vaadin"),
                new Anchor("https://google.com", "google"));

        setContent(new Button("content"));
    }
}