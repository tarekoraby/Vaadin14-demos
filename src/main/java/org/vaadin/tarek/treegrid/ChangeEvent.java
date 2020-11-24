package org.vaadin.tarek.treegrid;

import com.vaadin.flow.component.ComponentEvent;

public class ChangeEvent extends ComponentEvent<EnhancedTreeGrid> {
    public ChangeEvent(EnhancedTreeGrid source, boolean fromClient) {
        super(source, fromClient);
    }
}
