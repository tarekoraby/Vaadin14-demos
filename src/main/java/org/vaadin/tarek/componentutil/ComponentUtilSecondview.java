package org.vaadin.tarek.componentutil;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("ComponentUtilSecondView")
public class ComponentUtilSecondview extends VerticalLayout {

    public ComponentUtilSecondview() {
        TextField textField = new TextField("Your name");

        Button load = new Button("Get from UI",
                e -> textField.setValue(getMyVariables().getName()));

        add(textField, load);

    }

    public static MyVariables getMyVariables() {

        if (ComponentUtil.getData(UI.getCurrent(), MyVariables.class) == null) {
            ComponentUtil.setData(UI.getCurrent(), MyVariables.class,
                    new MyVariables());
        }
        return ComponentUtil.getData(UI.getCurrent(), MyVariables.class);
    }
}