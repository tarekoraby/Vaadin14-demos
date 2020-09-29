package org.vaadin.tarek.confirmdialog;

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
        ConfirmDialog dialog = new ConfirmDialog("Confirm publish",
                "Are you sure you want to publish the article?", "Publish",
                e -> onPublish(), "Cancel", c -> onCancel());
        Button button = new Button("Open dialog");
        button.addClickListener(event -> dialog.open());
        add(button, dialog);
    }

    private void onCancel() {
        Notification.show("Cancelled");
    }

    private void onPublish() {
        Notification.show("Published");
    }
}
