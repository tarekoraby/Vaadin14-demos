package com.example.test.urlparameters;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("UrlParametersView")
public class UrlParametersView extends VerticalLayout implements HasUrlParameter<String> {

	Span span;

	public UrlParametersView() {
		span = new Span();
		add(new H3("Parameters:"), span);
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		if (parameter == null) {
			span.setText("Welcome anonymous.");
		} else {
			span.setText(String.format("Welcome %s.", parameter));
		}
	}
}
