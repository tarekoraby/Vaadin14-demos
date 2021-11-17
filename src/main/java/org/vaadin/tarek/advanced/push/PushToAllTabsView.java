package org.vaadin.tarek.advanced.push;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.Collection;

@Route("PushToAllTabsView")
@Push
public class PushToAllTabsView extends VerticalLayout {

    ProgressBar progressBar;

    public PushToAllTabsView() {
        ProgressBar progressBar = new ProgressBar(0, 100, 0);
        Button button = new Button("Start backend task!");
        button.addClickListener(event -> executeTask());


        add(button, progressBar);
    }

    private void executeTask() {
        Collection<UI> uiCollection = VaadinSession.getCurrent().getUIs();
        for (UI ui : uiCollection) {
            PushToAllTabsView pushView = (PushToAllTabsView) ui.getChildren().filter(c -> c instanceof PushToAllTabsView).findFirst()
                    .orElse(null);
            if (pushView != null) {
                updateUI(ui, pushView);
            }
        }
    }

    private void updateUI(UI ui, PushToAllTabsView pushView) {
        ProgressBar progressBar = (ProgressBar) pushView.getChildren().filter(c -> c instanceof ProgressBar).findFirst()
                .orElse(null);
        if (progressBar == null || progressBar.getValue() >= 100)
            return;

        Thread one = new Thread() {
            @Override
            public void run() {
                while (progressBar.getValue() < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (progressBar.getValue() < 100) {
                        ui.access(() -> {
                            progressBar.setValue(progressBar.getValue() + 1);
                        });
                    }
                }
                ui.access(() -> pushView.add(new Span("Done!")));
            }
        };

        one.start();
    }
}
