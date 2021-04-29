package pl.qubiak.photoHosting.Giu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.qubiak.photoHosting.Service.ImageUploader;

@Route("upload")
public class UploadViev extends VerticalLayout {

    private ImageUploader imageUploader;

    @Autowired
    public UploadViev(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(clickEvent ->
        {
            String uploadedImage = imageUploader.uploadFileAndSaveToDb(textField.getValue());
            Image image = new Image(uploadedImage, "nie ma obrazka :(");
            label.setText("Udało się wrzucić obrazek!!!!!!!!");
            add(label);
            add(image);

        });

        add(textField);
        add(button);

    }
}
