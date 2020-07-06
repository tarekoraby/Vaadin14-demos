package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.DomEvent;
import com.vaadin.flow.router.Route;

@Route("ElementAPIView")
public class ElementAPIView extends VerticalLayout {
	private H1 text;

	public ElementAPIView() {
		setWidthFull();
		getStyle().set("border", "1px solid black");
		setPadding(true);
		setMargin(true);
		add(new Span("This is a vertical layout"));

		this.text = new H1("Click event data will appear here!");
		add(this.text);

		getElement().addEventListener("click", this::handleClickEvent)
				.addEventData("element.offsetWidth")
				.addEventData("element.offsetHeight")
				.addEventData("event.screenX").addEventData("event.screenY")
				.addEventData("event.ctrlKey");
	}

	private void handleClickEvent(DomEvent e) {
		this.text.setText("Click event data " + e.getEventData().toString());

	}
}
