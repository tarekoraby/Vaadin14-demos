package org.vaadin.tarek.accordion;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("AccordionView")
public class AccordionView extends VerticalLayout {

    public AccordionView() {
        Accordion accordion = new Accordion();

        VerticalLayout personalInformationLayout = new VerticalLayout();
        personalInformationLayout.add(new TextField("Name"),
                new TextField("Phone"), new TextField("Email"));
        accordion.add("Personal Information", personalInformationLayout);

        VerticalLayout billingAddressLayout = new VerticalLayout();
        billingAddressLayout.add(new TextField("Address"),
                new TextField("City"), new TextField("State"),
                new TextField("Zip Code"));
        accordion.add("Billing Address", billingAddressLayout);

        VerticalLayout paymenLayout = new VerticalLayout();
        paymenLayout.add(new Span("Not yet implemented"));
        AccordionPanel billingAddressPanel = accordion.add("Payment",
                paymenLayout);
        billingAddressPanel.setEnabled(false);

        add(accordion);
    }

}
