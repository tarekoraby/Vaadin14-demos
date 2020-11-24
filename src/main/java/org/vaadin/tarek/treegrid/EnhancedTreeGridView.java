package org.vaadin.tarek.treegrid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.router.Route;

@Route("EnhancedTreeGridView")
public class EnhancedTreeGridView extends VerticalLayout {

    public EnhancedTreeGridView() {
        EnhancedTreeGrid<String> enhancedTreeGrid = new EnhancedTreeGrid<>();
        enhancedTreeGrid.addHierarchyColumn(String::toString)
                .setHeader("Header");

        TreeData<String> treeData = enhancedTreeGrid.getTreeData();
        treeData.addItem(null, "Parent #1");
        treeData.addItem("Parent #1", "Item #1");
        treeData.addItem("Parent #1", "Item #2 (Non-selectable)");
        treeData.addItem("Parent #1", "Item #3");
        treeData.addItem(null, "Parent #2 (Non-selectable)");
        treeData.addItem("Parent #2 (Non-selectable)", "Item #4");
        treeData.addItem("Parent #2 (Non-selectable)",
                "Item #5 (Non-selectable)");
        enhancedTreeGrid.expand(treeData.getRootItems());

        enhancedTreeGrid.setSelectabilityCriteria(
                item -> !item.toString().contains("Non-selectable"));


        add(enhancedTreeGrid);

        enhancedTreeGrid.addValueChangeListener(
                e -> System.out.println(enhancedTreeGrid.getSelectedItems()));
    }
}
