package org.vaadin.tarek.components.custom;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Route("CustomFieldView")
public class CustomFieldView extends VerticalLayout {

	Paragraph p;

	public CustomFieldView() {

		PhoneNumberField phoneNumField = new PhoneNumberField();
		phoneNumField.addPhoneNumberCompletedListener(
				e -> this.p.setText("PhoneNumber completed!!!"));

		this.p = new Paragraph(
				"When phone number is completed (including a seven-digit"
						+ " subscriber code), a phone-number-completed event will be triggered");
		add(phoneNumField, this.p);
	}

	public static class PhoneNumberField extends CustomField<String> {
		private final Select countryCode = new Select();
		private final TextField areaCode = new TextField();
		private final TextField subscriberCode = new TextField();

		PhoneNumberField() {
			setLabel("Phone number");
			this.countryCode.setItems("+358", "+46", "+34");
			this.countryCode.getStyle().set("width", "6em");
			this.countryCode.setPlaceholder("Code");
			this.areaCode.setPattern("[0-9]*");
			this.areaCode.setPreventInvalidInput(true);
			this.areaCode.setMaxLength(4);
			this.areaCode.setPlaceholder("Area");
			this.areaCode.getStyle().set("width", "5em");
			this.subscriberCode.setPattern("[0-9]*");
			this.subscriberCode.setPreventInvalidInput(true);
			this.subscriberCode.setMaxLength(8);
			this.subscriberCode.setPlaceholder("Subscriber");
			HorizontalLayout horizontalLayout = new HorizontalLayout();
			horizontalLayout.add(this.countryCode, this.areaCode,
					this.subscriberCode);
			add(horizontalLayout);

			addValueChangeListener(e -> {
				if (this.countryCode.getValue() != null
						&& this.areaCode.getValue() != null
						&& this.subscriberCode.getValue().length() == 7) {
					PhoneNumberCompletedEvent event = new PhoneNumberCompletedEvent(
							this, false);
					fireEvent(event);
				}
			});
		}

		public Registration addPhoneNumberCompletedListener(
				ComponentEventListener<PhoneNumberCompletedEvent> listener) {
			return addListener(PhoneNumberCompletedEvent.class, listener);
		}

		@Override
		protected String generateModelValue() {
			return this.countryCode.getValue() + " " + this.areaCode.getValue()
					+ " " + this.subscriberCode.getValue();
		}

		@Override
		protected void setPresentationValue(String newPresentationValue) {
			if (newPresentationValue == null) {
				this.areaCode.setValue(null);
				this.subscriberCode.setValue(null);
			}
		}
	}
}