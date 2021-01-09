package org.vaadin.tarek.menubar;

import java.util.stream.Stream;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.Route;

@Route("MenuBarSimpleView")
public class MenuBarSimpleView extends Div {

    public MenuBarSimpleView() {
        MenuBar menuBar = new MenuBar();
        Stream.of("Home", "Dashboard", "Content", "Structure", "Appearance", "Modules", "Users", "Configuration",
                "Reports", "Help").forEach(menuBar::addItem);

        add(menuBar);
    }

}
