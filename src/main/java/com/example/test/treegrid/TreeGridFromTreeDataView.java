package com.example.test.treegrid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.router.Route;

@Route("TreeGridFromTreeDataView")
public class TreeGridFromTreeDataView extends VerticalLayout {

    public TreeGridFromTreeDataView() {
        TreeGrid<String> grid = new TreeGrid<>();
        grid.addHierarchyColumn(String::toString).setHeader("Name");

        String root = "Root";
        String child = "child";
        String subChild = "Sub-child";

        TreeData<String> td = grid.getTreeData();
        td.addItem(null, root);
        td.addItem(root, child);
        td.addItem(child, subChild);
        grid.expand(root, child, subChild);

        add(grid);
    }
}
