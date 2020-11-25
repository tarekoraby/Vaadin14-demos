package org.vaadin.tarek.confirmdialog;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ConfirmDialogView")
@CssImport(value = "./styles/vaadin-confirm-dialog-styles.css", themeFor = "vaadin-confirm-dialog")
public class ConfirmDialogView extends VerticalLayout {

    public ConfirmDialogView() {
        ConfirmDialog simpleDialog = createDialog();
        Button buttonA = new Button("Open simple dialog",
                event -> simpleDialog.open());

        ConfirmDialog styledMessageDialog = createDialog();
        String themeName = "myConfirmDialog";
        styledMessageDialog.getElement().setAttribute("theme", themeName);
        Button buttonB = new Button("Open dialog wih styled message", e -> {
            styledMessageDialog.open();
            String color = "green";
            UI.getCurrent().getPage().executeJs(
                    "document.querySelector(\"[theme='_vaadin-confirm-dialog-dialog-overlay-theme "
                            + themeName
                            + "']\").$.content.shadowRoot.querySelector(\"[part='message']\").style.setProperty('color', '"
                            + color + "')");
        });

        add(buttonA, buttonB);
    }

    private ConfirmDialog createDialog() {
        return new ConfirmDialog("Confirm publish",
                "Are you sure you want to publish the article?", "Publish",
                e -> onPublish(), "Cancel", c -> onCancel());
    }

    private void onCancel() {
        Notification.show("Cancelled");
    }

    private void onPublish() {
        Notification.show("Published");
    }
}
