package org.vaadin.tarek.upload;

import org.vaadin.tarek.GreetService;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

@Route("uploadview")
@CssImport(value = "./styles/vaadin-upload-styles.css", themeFor = "vaadin-upload")
public class UploadView extends VerticalLayout {

    public UploadView() {
        // Use TextField for standard text input
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            Notification.show("Upload complete of " + event.getFileName());
        });

        upload.addFileRejectedListener(event -> {
            Notification.show("Upload failed: " + event.getErrorMessage());
        });

        upload.getElement().addEventListener("upload-before",
                e -> System.out.println("upload-before event"));

        add(upload);

        // Use TextField for standard text input
        TextField textField = new TextField("Your name");

        // Button click listeners can be defined as lambda expressions
        GreetService greetService = new GreetService();
        Button button = new Button("Say hello", e -> Notification
                .show(greetService.greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in
        // shared-styles.css.
        addClassName("centered-content");

        add(textField, button);
    }
}
