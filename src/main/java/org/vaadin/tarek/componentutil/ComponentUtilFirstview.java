package org.vaadin.tarek.componentutil;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("ComponentUtilFirstView")
public class ComponentUtilFirstview extends VerticalLayout {

    public ComponentUtilFirstview() {
        TextField textField = new TextField("Your name");

        Button save = new Button("Save to UI",
                e -> getMyVariables().setName(textField.getValue()));
        RouterLink link = new RouterLink("Go to view2", ComponentUtilSecondview.class);


        add(textField, save, link);
    }

    public static MyVariables getMyVariables() {

        if (ComponentUtil.getData(UI.getCurrent(), MyVariables.class) == null) {
            ComponentUtil.setData(UI.getCurrent(), MyVariables.class,
                    new MyVariables());
        }

        return ComponentUtil.getData(UI.getCurrent(), MyVariables.class);
    }
}