package org.vaadin.tarek.treegrid;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.shared.Registration;

public class EnhancedTreeGrid<T> extends TreeGrid<T> {

    private final Set<T> selectedItems;
    private Predicate<T> isSelectablePredicate;

    private final Set<Registration> registeredListeners = new HashSet<>();

    public EnhancedTreeGrid() {
        super();

        setSelectionMode(SelectionMode.NONE);
        selectedItems = new HashSet<>();

        // add selection column
        Column<T> selectionColumn = addComponentColumn(item -> {
            if (!isSelectablePredicate.test(item)) {
                return new Div();
            }

            Checkbox checkBox = new Checkbox();
            checkBox.addValueChangeListener(
                    e -> handleValueChangeEvent(e, item));

            Boolean isSelected = getSelectedItems().contains(item);
            checkBox.setValue(isSelected);
            return checkBox;
        });
        selectionColumn.setWidth("75px").setFlexGrow(0);
    }

    public void setSelectabilityCriteria(Predicate<T> isSelectablePredicate) {
        this.isSelectablePredicate = isSelectablePredicate;
    }

    @Override
    public void select(T item) {
        if (isSelectablePredicate.test(item)) {
            selectedItems.add(item);
            fireEvent(new ChangeEvent(this, true));
        }
    }

    @Override
    public void deselect(T item) {
        if (isSelectablePredicate.test(item)) {
            selectedItems.remove(item);
            fireEvent(new ChangeEvent(this, true));
        }
    }

    @Override
    public Set<T> getSelectedItems() {
        return Collections.unmodifiableSet(new LinkedHashSet<>(selectedItems));
    }

    @Override
    public void deselectAll() {
        selectedItems.clear();
    }

    public Registration addValueChangeListener(
            ComponentEventListener<ChangeEvent> listener) {
        Registration newListener = addListener(ChangeEvent.class, listener);
        registeredListeners.add(newListener);
        return newListener;
    }

    private void handleValueChangeEvent(
            ComponentValueChangeEvent<Checkbox, Boolean> e, T item) {
        if (!e.isFromClient()) {
            return;
        }

        Boolean isSelected = e.getValue();
        if (isSelected) {
            select(item);
        } else {
            deselect(item);
        }
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        for (Registration registration : registeredListeners) {
            registration.remove();
        }
    }

}
