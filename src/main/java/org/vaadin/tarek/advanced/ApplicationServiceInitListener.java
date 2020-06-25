package org.vaadin.tarek.advanced;

import java.time.Instant;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ApplicationServiceInitListener
implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        // VaadinService initialization occurs when the first request arrives to
        // the server after startup, once per application
        System.out.println("VaadinService initialized!");

        serviceInitEvent.getSource().addUIInitListener(event -> {
            UI ui = event.getUI();
            System.out.println("New UI instantiated. UI id # " + ui.getUIId()
                    + " " + Instant.now() + " " + ui.toString());
            // ui.getLoadingIndicatorConfiguration().setApplyDefaultTheme(false);
        });

        serviceInitEvent.getSource().addSessionInitListener(initEvent -> {
            System.out.println("New Session initialized"
                    + initEvent.getSession().toString());
        });

        serviceInitEvent.getSource().addSessionDestroyListener(destroyEvent -> {
            System.out.println("Session Destroyed "
                    + destroyEvent.getSession().toString());
        });
    }

}
