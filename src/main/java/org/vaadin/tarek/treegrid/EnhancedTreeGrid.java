package org.vaadin.tarek.treegrid;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.treegrid.TreeGrid;

public class EnhancedTreeGrid<T> extends TreeGrid<T> {

	private final Set<T> selectedItems;

    public EnhancedTreeGrid() {
        super();
        selectedItems = new HashSet<>();

        // add selection column
        Column<T> selectionColumn = addComponentColumn(item -> {
            Checkbox checkBox = new Checkbox();
            checkBox.addValueChangeListener(
                    e -> handleValueChangeEvent(e, item));

            Boolean isSelected = getSelectedItems().contains(item);
            checkBox.setValue(isSelected);
            if (!isSelected && isAnyChildSelected(item)) {
                checkBox.setIndeterminate(true);
            }
            return checkBox;
        });
        selectionColumn.setWidth("75px").setFlexGrow(0);
    }

    @Override
    public void select(T item) {
    	getSelectedItems().add(item);

        if (isRootItem(item)) {
            List<T> children = getChildren(item);
            for (T child : children) {
            	getSelectedItems().add(child);
            	getDataProvider().refreshItem(child);
            }
        } else {
            T parentItem = getParent(item);
            if (areAllChildrenSelected(parentItem)) {
            	getSelectedItems().add(parentItem);
            }
            getDataProvider().refreshItem(parentItem);
        }

        getDataProvider().refreshItem(item);
    }

    @Override
    public void deselect(T item) {
    	getSelectedItems().remove(item);

        if (isRootItem(item)) {
            List<T> children = getChildren(item);
            for (T child : children) {
            	getSelectedItems().remove(child);
            	getDataProvider().refreshItem(child);
            }
        } else {
            T parentItem = getParent(item);
            getSelectedItems().remove(parentItem);
            getDataProvider().refreshItem(parentItem);
        }

        getDataProvider().refreshItem(item);
    }

    @Override
    public Set<T> getSelectedItems() {
        return selectedItems;
    }

    @Override
    public void deselectAll() {
        selectedItems.clear();
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

    private boolean areAllChildrenSelected(T parentItem) {
        return getTreeData().getChildren(parentItem).stream()
                .allMatch(getSelectedItems()::contains);
    }

    private boolean isAnyChildSelected(T parentItem) {
        List<T> children = getChildren(parentItem);
        for (T child : children) {
            if (getSelectedItems().contains(child)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRootItem(T item) {
        return getTreeData().getRootItems().contains(item);
    }

    private T getParent(T item) {
        return getTreeData().getParent(item);
    }

    private List<T> getChildren(T item) {
        return getTreeData().getChildren(item);
    }
}
