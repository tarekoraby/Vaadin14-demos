package org.vaadin.tarek.upload;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

@Route("uploadview")
@CssImport(value = "./styles/vaadin-upload-styles.css", themeFor = "vaadin-upload")
public class UploadView extends VerticalLayout {

    public UploadView() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            Notification.show("Upload complete of " + event.getFileName());
        });

        upload.addFileRejectedListener(event -> {
            Notification.show("Upload failed: " + event.getErrorMessage());
        });

        Button clear = new Button("Clear upload list",
                e -> upload.getElement().executeJs("this.files=[]"));

        Upload uploadStyled = new Upload(buffer);
        uploadStyled.setClassName("styled-upload");

        Upload uploadWithExtraButton = new Upload(buffer);
        Button button = new Button("Extra button",
                e -> Notification.show("Button cliked!"));
        uploadWithExtraButton.getElement().appendChild(button.getElement());
        uploadWithExtraButton.getElement().executeJs(
                "var addFilesDiv = $0.shadowRoot.querySelector('[part=\"primary-buttons\"]');\r\n"
                        + "        var newButton = $1; \r\n"
                        + "        addFilesDiv.insertBefore(newButton, addFilesDiv.firstElementChild);",
                uploadWithExtraButton, button);

        Upload uploadWithCustomButton = new Upload(buffer);

        Button uploadButton = new Button("Click me!", e -> System.out.println("Upload button clicked!"));
        uploadWithCustomButton.setUploadButton(uploadButton);

        add(upload, clear, uploadStyled, uploadWithExtraButton, uploadWithCustomButton);
    }
}
