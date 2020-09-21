package org.vaadin.tarek.upload;

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

        upload.getElement().addEventListener("upload-before",
                e -> System.out.println("upload-before event"));

        add(upload);
    }
}
