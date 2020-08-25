package org.vaadin.tarek.advanced;

import java.time.Instant;

import org.vaadin.tarek.advanced.security.LoginView;
import org.vaadin.tarek.advanced.security.SecurityUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.CustomizedSystemMessages;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.shrinkwrap.VaadinCoreShrinkWrap;

@SpringComponent
public class ApplicationServiceInitListener
        implements VaadinServiceInitListener {

    int maxInactiveInterval = 1800;

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {

        serviceInitEvent.addBootstrapListener(new CustomBootstrapListener());

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
            // ui.addBeforeEnterListener(this::authenticateNavigation);
            // print("VaadinService.get.getContextPath()" +
            // VaadinService.getCurrentRequest().getContextPath());
            ui.addDetachListener(
                    e -> print("UI detached. UI id # " + ui.getUIId() + " "
                            + Instant.now() + " " + ui.toString()));
            /*
             * UI.getCurrent().getPushConfiguration().setParameter("timeout",
             * "-1");
             */
        });

        serviceInitEvent.getSource().addUIInitListener(event -> {
            UI ui = event.getUI();
            ui.addAttachListener(attachEvent -> {
                if (!isMobileDevice()) {
                    ui.getPage().executeJs(
                            "document.querySelector('link[rel=\"manifest\"]').remove();");
                }
            });
        });

        serviceInitEvent.getSource().addUIInitListener(event -> {
            UI ui = event.getUI();
            ui.addBeforeEnterListener(beforeEvent -> {
                ui.getPage().executeJs(
                        "url1 = window.location.toString(); url2 = url1.replace('#!', ''); if (url1 !== url2) window.location.replace(url2);");
            });
        });

        serviceInitEvent.getSource().addSessionInitListener(initEvent -> {
            print("New Session initialized " + Instant.now() + " "
                    + initEvent.getSession().toString());

            initEvent.getSession().getSession()
                    .setMaxInactiveInterval(maxInactiveInterval);

            initEvent.getService()
                    .setSystemMessagesProvider(systemMessagesInfo -> {
                        CustomizedSystemMessages customizedSystemMessages = new CustomizedSystemMessages();
                        customizedSystemMessages.setSessionExpiredURL("logout");
                        return customizedSystemMessages;
                    });
        });

        serviceInitEvent.getSource().addSessionDestroyListener(destroyEvent -> {
            print("Session Destroyed " + Instant.now() + " "
                    + destroyEvent.getSession().toString());
            invalidateAuthentication();
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        System.out.println("here " + event.getNavigationTarget());
        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }

    private void invalidateAuthentication() {
        if (SecurityUtils.isUserLoggedIn()) {
            SecurityUtils.setAuthenticated(false);
        }
    }

    private void print(String string) {
        System.out.println(string);
    }

    public boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone()
                || webBrowser.isWindowsPhone();
    }

}
