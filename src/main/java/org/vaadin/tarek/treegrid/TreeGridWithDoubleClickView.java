package org.vaadin.tarek.treegrid;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.router.Route;

@Route("TreeGridWithDoubleClickView")
@CssImport(value = "./styles/vaadin-grid-tree-toggle-styles.css", themeFor = "vaadin-grid-tree-toggle")
public class TreeGridWithDoubleClickView extends VerticalLayout {

    public TreeGridWithDoubleClickView() {
        TreeGrid<String> grid = new TreeGrid<>();

        String root = "Root";
        String child = "child";
        String subChild = "Sub-child";

        TreeData<String> td = grid.getTreeData();
        td.addItem(null, root);
        td.addItem(root, child);
        td.addItem(child, subChild);
        grid.expand(root, child, subChild);

        grid.addComponentHierarchyColumn(item -> {
            Div div = new Div(new Icon(VaadinIcon.VAADIN_H), new Span(item));

            div.getElement().addEventListener("dblclick", e -> {
                Dialog details = new Dialog();
                details.add(new Span(item));
                details.open();
            });

            div.getElement()
                    .executeJs("$0.addEventListener('click', function(e) {"
                            + "e.stopPropagation();});", div);

            div.getStyle().set("pointer-events", "auto");
            return div;
        }).setHeader("Header");

        add(grid);
    }
}
