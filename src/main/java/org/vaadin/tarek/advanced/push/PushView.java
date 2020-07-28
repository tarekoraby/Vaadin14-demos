package org.vaadin.tarek.advanced.push;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

@Route("PushView")
@Push
public class PushView extends VerticalLayout {

    ProgressBar progressBar;

    public PushView() {
        progressBar = new ProgressBar(0, 100, 0);
        Button button = new Button("Start backend task!");
        button.addClickListener(event -> exucuteTask());

        add(button, progressBar);
    }

    private void exucuteTask() {
        Thread one = new Thread() {
            @Override
            public void run() {
                UI ui = getUI().get();
                while (progressBar.getValue() < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ui.access(() -> {
                        progressBar.setValue(progressBar.getValue() + 1);
                    });
                }
                ui.access(() -> add(new Span("Done!")));
            }
        };

        one.start();
    }

}
