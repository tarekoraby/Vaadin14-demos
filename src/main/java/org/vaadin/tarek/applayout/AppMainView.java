package org.vaadin.tarek.applayout;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;

public class AppMainView extends AppLayout implements AfterNavigationObserver {

    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();


    public AppMainView() {
        addMenuTab("Main (accepts optional parameter)",
                AppWithOptionalParametersView.class);
        addMenuTab("Admin", AppAdminView.class);
        addMenuTab("Dashboard", AppDashboardView.class);
        this.tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(this.tabs);
        addToNavbar(new DrawerToggle());
    }

    private void addMenuTab(String label, Class<? extends Component> target) {
        Tab tab = new Tab(new RouterLink(label, target));
        this.navigationTargetToTab.put(target, tab);
        this.tabs.add(tab);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        getElement().executeJs(
                "this.shadowRoot.querySelector(\"[content]\").scrollTop=0");
    }
}