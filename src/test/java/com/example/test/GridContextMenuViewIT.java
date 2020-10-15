package com.example.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.flow.component.grid.testbench.TreeGridElement;
import com.vaadin.flow.component.html.testbench.LabelElement;

public class GridContextMenuViewIT extends AbstractViewTest {

    @Before
    public void openUrl() throws Exception {
        getDriver().get("http://localhost:8080/GridContextMenuView");
    }

    @Test
    public void testContextMenu() throws InterruptedException {
        TreeGridElement treeGridElement = $(TreeGridElement.class).first();
        LabelElement labelElement = $(LabelElement.class).first();

        treeGridElement.getCell(1, 0).contextClick();
        findElement(By.tagName("vaadin-context-menu-item")).click();
        Thread.sleep(1000);
        Assert.assertEquals("Clicked on item: Samuel", labelElement.getText());
    }
}