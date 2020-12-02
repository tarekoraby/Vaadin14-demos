package org.vaadin.tarek.i18n;

import java.util.Locale;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("I18NView")
public class I18NView extends VerticalLayout implements LocaleChangeObserver {

    private final Button button = new Button();

    @Override
    public void localeChange(LocaleChangeEvent event) {
        button.setText(getTranslation("btn.key"));
    }

    public I18NView() {
        button.setText(getTranslation("btn.key"));
        button.addClickListener(
                event -> Notification.show(getTranslation("clicked")));

        ComboBox<Locale> localeComboBox = new ComboBox<>();
        localeComboBox.setItems(Locale.ENGLISH, new Locale("fi"),
                new Locale("hi", "IN"));
        localeComboBox.setValue(Locale.ENGLISH);
        localeComboBox.addValueChangeListener(event -> {
            // change the locale in the VaadinSession
            VaadinSession.getCurrent().setLocale(event.getValue());
        });

        add(localeComboBox, button);
    }

}
