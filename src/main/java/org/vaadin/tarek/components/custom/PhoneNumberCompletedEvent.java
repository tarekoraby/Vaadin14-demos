package org.vaadin.tarek.components.custom;

import org.vaadin.tarek.components.custom.CustomFieldView.PhoneNumberField;

import com.vaadin.flow.component.ComponentEvent;

public class PhoneNumberCompletedEvent extends ComponentEvent<PhoneNumberField> {
	public PhoneNumberCompletedEvent(PhoneNumberField source, boolean fromClient) {
		super(source, fromClient);
	}
}