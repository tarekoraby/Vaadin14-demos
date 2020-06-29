package org.vaadin.tarek.advanced;

import java.time.Instant;

import org.vaadin.tarek.advanced.security.LoginView;
import org.vaadin.tarek.advanced.security.SecurityUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.shrinkwrap.VaadinCoreShrinkWrap;

@SpringComponent
public class ApplicationServiceInitListener
implements VaadinServiceInitListener {

    int maxInactiveInterval = 1800;

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        // VaadinService initialization occurs when the first request arrives to
        // the server after startup, once per application
        print("VaadinService initialized! " + Instant.now());
        print("Vaadin version: " + VaadinCoreShrinkWrap.class
                .getAnnotation(NpmPackage.class).version());

        print("DeploymentConfiguration.isCloseIdleSessions() "
                + VaadinService.getCurrent().getDeploymentConfiguration()
                .isCloseIdleSessions());
        print("DeploymenntConfiguration.getHeartbeatInterval() "
                + VaadinService.getCurrent().getDeploymentConfiguration()
                .getHeartbeatInterval());
        print("HTTP sessions maxInactiveInterval: " + maxInactiveInterval);

        serviceInitEvent.getSource().addUIInitListener(event -> {
            UI ui = event.getUI();
            print("New UI instantiated. UI id # " + ui.getUIId() + " "
                    + Instant.now() + " " + ui.toString());
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });

        serviceInitEvent.getSource().addSessionInitListener(initEvent -> {
            print("New Session initialized " + Instant.now() + " "
                    + initEvent.getSession().toString());

            initEvent.getSession().getSession()
            .setMaxInactiveInterval(maxInactiveInterval);
        });

        serviceInitEvent.getSource().addSessionDestroyListener(destroyEvent -> {
            print("Session Destroyed " + Instant.now()
            + " "
            + destroyEvent.getSession().toString());
            invalidateAuthentication();
        });
    }

    private void invalidateAuthentication() {
        if (SecurityUtils.isUserLoggedIn()) {
            SecurityUtils.setAuthenticated(false);
        }
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }

    private void print(String string) {
        System.out.println(string);
    }

}
