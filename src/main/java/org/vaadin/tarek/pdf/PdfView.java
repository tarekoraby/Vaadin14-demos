package org.vaadin.tarek.pdf;

import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("PdfView")
public class PdfView extends VerticalLayout {

    public PdfView() {
        IFrame iFrame = new IFrame();
        iFrame.setWidth("500px");
        iFrame.setHeight("500px");
        iFrame.setSrc("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        add(iFrame);
    }
}
