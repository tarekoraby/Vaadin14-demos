package org.vaadin.tarek.advanced;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ReconnectDialogConfigurationView")
public class ReconnectDialogConfigurationView extends VerticalLayout {

    public ReconnectDialogConfigurationView() {
        UI ui = UI.getCurrent();

        IntegerField reconnectInterval = new IntegerField("Reconnect Interval");
        reconnectInterval.setValue(ui.getReconnectDialogConfiguration().getReconnectInterval());
        reconnectInterval
                .addValueChangeListener(e -> ui.getReconnectDialogConfiguration().setReconnectInterval(e.getValue()));

        IntegerField reconnectAttempts = new IntegerField("Reconnect Attempts");
        reconnectAttempts.setValue(ui.getReconnectDialogConfiguration().getReconnectAttempts());
        reconnectAttempts
                .addValueChangeListener(e -> ui.getReconnectDialogConfiguration().setReconnectAttempts(e.getValue()));

        TextField dialogText = new TextField("Reconnect Dialog Text");
        dialogText.setValue(ui.getReconnectDialogConfiguration().getDialogText());
        dialogText.addValueChangeListener(e -> ui.getReconnectDialogConfiguration().setDialogText(e.getValue()));
        dialogText.setWidth("400px");

        TextField dialogTextGaveUp = new TextField("Reconnect Dialog Text GaveUp");
        dialogTextGaveUp.setValue(ui.getReconnectDialogConfiguration().getDialogTextGaveUp());
        dialogTextGaveUp
                .addValueChangeListener(e -> ui.getReconnectDialogConfiguration().setDialogTextGaveUp(e.getValue()));
        dialogTextGaveUp.setWidth("400px");

        add(reconnectInterval, reconnectAttempts, dialogText, dialogTextGaveUp);
    }

}
