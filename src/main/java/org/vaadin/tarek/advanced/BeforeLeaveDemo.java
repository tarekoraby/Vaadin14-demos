package org.vaadin.tarek.advanced;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveEvent.ContinueNavigationAction;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

@Route("BeforeLeaveDemo")
public class BeforeLeaveDemo extends VerticalLayout {

    VerticalLayout layout;

    public BeforeLeaveDemo() {

        Button button = new Button("navigate away", e -> UI.getCurrent().navigate(""));
        layout = this;

        add(button, new A(), new B(), new C());
    }

    class A extends Div implements BeforeLeaveObserver {

        @Override
        public void beforeLeave(BeforeLeaveEvent event) {
            layout.add(new Span("New navigation transition;"));
            layout.add(new Span("A"));
        }

    }

    class B extends Div implements BeforeLeaveObserver {

        @Override
        public void beforeLeave(BeforeLeaveEvent event) {
            layout.add(new Span("B"));
            ContinueNavigationAction action = event.postpone();
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setText("Continue navigation?");
            confirmDialog.setCancelable(true);
            confirmDialog.addConfirmListener(e -> action.proceed());
            confirmDialog.open();
        }
    }

    class C extends Div implements BeforeLeaveObserver {

        @Override
        public void beforeLeave(BeforeLeaveEvent event) {
            layout.add(new Span("C"));
            event.postpone();
        }
    }
}
