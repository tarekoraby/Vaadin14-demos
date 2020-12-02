package com.example.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.IPAddress;

public class BenchViewIT extends AbstractViewTest {

    @Test
    public void foo() {
        TextFieldElement textElement = $(TextFieldElement.class).first();

        // close the Dialog by outside click
        textElement.click();

        String value = textElement.getValue();
        Assert.assertEquals("myValue", value);
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
                + ":8080/TestBenchView");
    }
}
