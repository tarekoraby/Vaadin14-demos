package org.vaadin.tarek.notification;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("NotificationView")
@CssImport(value = "./styles/vaadin-notification-card-styles.css", themeFor = "vaadin-notification-card")
public class NotificationView extends VerticalLayout {

    public NotificationView() {

        CustomNotification notification = new CustomNotification("hello", Integer.MAX_VALUE, "custom-notification");

        Button button = new Button("Say hello", e -> notification.open());
        add(button);
    }

    class CustomNotification extends Notification {

        public CustomNotification(String txt, int duration, String themeName) {
            super(txt, duration);
            setThemeName(themeName);
        }

    }

}
