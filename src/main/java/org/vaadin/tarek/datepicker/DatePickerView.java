package org.vaadin.tarek.datepicker;

import java.time.LocalDate;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("DatePickerView")
@CssImport(value = "./styles/vaadin-date-picker-styles.css", themeFor = "vaadin-date-picker")
public class DatePickerView extends VerticalLayout {

    public DatePickerView() {
        DatePicker labelDatePicker = new DatePicker();
        labelDatePicker.setLabel("Label");

        DatePicker placeholderDatePicker = new DatePicker();
        placeholderDatePicker.setPlaceholder("Placeholder");

        DatePicker valueDatePicker = new DatePicker();
        LocalDate now = LocalDate.now();
        valueDatePicker.setValue(now);

        add(labelDatePicker, placeholderDatePicker, valueDatePicker);
    }

}
