package org.vaadin.tarek.advanced.push;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;

@Route("PollView")
public class PollView extends VerticalLayout {

    public PollView() {
        IntegerField numberField = new IntegerField("Poll interval (sec)");
        numberField.setValue(1);

        Button start = new Button("Start polling", e -> UI.getCurrent().setPollInterval(numberField.getValue() * 1000));
        Button stop = new Button("Stop polling", e -> UI.getCurrent().setPollInterval(-1));

        add(numberField, start, stop);

        UI.getCurrent().addPollListener(e -> add(new Span(
                "Server response at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")))));
    }


}
