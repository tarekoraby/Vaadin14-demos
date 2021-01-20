package org.vaadin.tarek.anchor;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("AnchorView")
public class AnchorView extends VerticalLayout {

    public AnchorView() {
        Anchor anchorOne = new Anchor("https://vaadin.com",
                "Open here...");

        Anchor anchorTwo = new Anchor("https://vaadin.com",
                "Open in new tab...");
        anchorTwo.setTarget("_blank");

        Anchor downloadLink = new Anchor("icons/icon-16x16.png", "Download");
        //downloadLink.getElement().setAttribute("download", true);

        add(anchorOne, anchorTwo, downloadLink);
    }
}
