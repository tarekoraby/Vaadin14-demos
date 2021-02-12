package org.vaadin.tarek.binder;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.AbstractValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("ValidationWithoutBinder")
public class ValidationWithoutBinder extends VerticalLayout {

    public ValidationWithoutBinder() {
        TextField textField = new TextField("Your name");
        textField.setValueChangeMode(ValueChangeMode.EAGER);

        AbstractValidator<String> validator = new StringLengthValidator("Not valid", 5, 10);

        textField.addValueChangeListener(event -> {

            ValidationResult result = validator.apply(event.getValue(), new ValueContext(textField));

            if (result.isError()) {
                textField.setErrorMessage(result.getErrorMessage());
                textField.setInvalid(true);
            } else {
                textField.setErrorMessage(null);
                textField.setInvalid(false);
            }
        });

        add(textField);
    }
}
