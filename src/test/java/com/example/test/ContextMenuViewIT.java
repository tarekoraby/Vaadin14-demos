package com.example.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.vaadin.flow.component.html.testbench.LabelElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.IPAddress;

public class ContextMenuViewIT extends AbstractViewTest {

    @Test
    public void clickingMenuItemsShouldChangeLabel() {
        $(TextFieldElement.class).first().contextClick();

        Actions action = new Actions(getDriver());
        WebElement menuListBox = getDriver()
                .findElement(By.className("vaadin-menu-list-box"));
        WebElement menuItemTwo = getDriver()
                .findElement(By.id("item-two"));
        action.moveToElement(menuListBox).moveToElement(menuItemTwo).click()
        .build().perform();

        final LabelElement label = $(
                LabelElement.class)
                .first();
        Assert.assertEquals("Clicked on the second item", label.getText());
    }

    @Override
    @Before
    public void setup() throws Exception {
        /**
         * To run in headless mode ChromeOptions options = new ChromeOptions();
         * options.addArguments("--headless", "--disable-gpu");
         * options.addArguments("--headless", "--disable-gpu"); setDriver(new
         * ChromeDriver(options));
         */
        setDriver(new ChromeDriver());
        // Open the application
        getDriver().get("http://" + IPAddress.findSiteLocalAddress()
        + ":8080/ContextMenuView");
    }
}
