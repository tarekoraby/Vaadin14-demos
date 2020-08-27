package org.vaadin.tarek.treegrid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.router.Route;

@Route("EnhancedTreeGridView")
public class EnhancedTreeGridView extends VerticalLayout {

    public EnhancedTreeGridView() {
        EnhancedTreeGrid<String> enhancedTreeGrid = new EnhancedTreeGrid<>();
        enhancedTreeGrid.addHierarchyColumn(String::toString)
                .setHeader("Exam name");

        TreeData<String> treeData = enhancedTreeGrid.getTreeData();
        treeData.addItem(null, "Exam #1");
        treeData.addItem("Exam #1", "Exam #1 - item #1");
        treeData.addItem("Exam #1", "Exam #1 - item #2");
        treeData.addItem("Exam #1", "Exam #1 - item #3");
        treeData.addItem(null, "Exam #2");
        treeData.addItem("Exam #2", "Exam #2 - item #1");
        treeData.addItem("Exam #2", "Exam #2 - item #2");

        enhancedTreeGrid.select("Exam #2 - item #2");
        enhancedTreeGrid.expand(treeData.getRootItems());

        add(enhancedTreeGrid);
    }
}
