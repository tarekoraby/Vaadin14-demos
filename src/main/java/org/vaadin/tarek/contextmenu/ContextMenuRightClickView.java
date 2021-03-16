package org.vaadin.tarek.contextmenu;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ContextMenuRightClickView")
public class ContextMenuRightClickView extends VerticalLayout {

    public ContextMenuRightClickView() {

        TextField textField = new TextField("Text Field with context-menu");

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setTarget(textField);

        contextMenu.addItem(new Anchor("https://vaadin.com", "Vaadin"));

        contextMenu.addOpenedChangeListener(e -> {
            if (e.isOpened()) {
                contextMenu.getElement().executeJs(
                        "document.documentElement.removeEventListener('contextmenu', this._boundOnGlobalContextMenu, true);"
                                + "this._setListenOnUserSelect('');");
            }
        });

        add(textField);
    }
}
