package org.vaadin.tarek.advanced;

import java.util.concurrent.TimeUnit;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("DisableLoadingIndicatorView")
public class DisableLoadingIndicatorView extends VerticalLayout {

    public DisableLoadingIndicatorView() {

        UI.getCurrent().getLoadingIndicatorConfiguration()
        .setApplyDefaultTheme(false);


        Button buttonDelay = new Button("Say a delayed hello", e -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Notification.show("Hello!");
        });

        add(buttonDelay);
    }

}
