package org.vaadin.tarek.layouts.split;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("SplitLayoutView")
@CssImport(value = "./styles/vaadin-split-layout-styles.css", themeFor = "vaadin-split-layout")
public class SplitLayoutView extends VerticalLayout {

    public SplitLayoutView() {
        new TextField("Your name");

        Span primaryComponent = new Span("Primary component");
        Span secondaryComponent = new Span("Secondary component");
        SplitLayout layout = new SplitLayout(primaryComponent,
                secondaryComponent);
        layout.setSizeFull();
        add(layout);
        setSizeFull();
    }
}
