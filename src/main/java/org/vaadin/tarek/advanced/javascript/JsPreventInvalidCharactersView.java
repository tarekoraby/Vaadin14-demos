package org.vaadin.tarek.advanced.javascript;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("JsPreventInvalidCharactersView")
public class JsPreventInvalidCharactersView extends VerticalLayout {

    public JsPreventInvalidCharactersView() {

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Only numbers and ' / ' are allowed:");
        datePicker.getElement()
                .executeJs(
                        "this.onkeypress = function(e) {var chr = String.fromCharCode(e.which);"
                                + " if ('123456789/'.indexOf(chr) < 0) return false;};");

        add(datePicker);

        datePicker.setWidth("600px");
        setWidthFull();
    }

}
