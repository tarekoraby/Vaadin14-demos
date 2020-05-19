package com.example.test.urlparameters;

import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("QueryParametersView")
public class QueryParametersView extends VerticalLayout implements HasUrlParameter<String>  {

	Span span;
	
    public QueryParametersView() {
    	span = new Span();
        add(new H3("Parameters:"),  span);
    }
    
	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

	    Location location = event.getLocation();
	    QueryParameters queryParameters = location
	            .getQueryParameters();

	    Map<String, List<String>> parametersMap =
	            queryParameters.getParameters();

	    span.setText(parametersMap.toString());
		
	}
}
