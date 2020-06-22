package org.vaadin.tarek.session.share;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


/**
 * The main view contains a button and a click listener.
 */
@Route("session/share/SecondView")
public class SecondView extends VerticalLayout {

    Grid<?> grid;

    public SecondView() {
        add("GRID VIEW");

        this.grid = (Grid<?>) VaadinSession.getCurrent().getAttribute("grid");
        if (grid==null) {
            add("NO DATA");
        } else {
            add(grid);
        }
    }

    void configureUndock() {
        add(new Button("Undock", e -> {
            grid.getElement().removeFromTree();
            UI.getCurrent().getPage().executeJs("window.open('grid')");
        }));
    }


}
