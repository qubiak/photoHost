package pl.qubiak.photoHosting.Giu;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.qubiak.photoHosting.Model.Image;
import pl.qubiak.photoHosting.Repo.ImageRepo;

import java.util.List;

@Route("gallery")
public class GalleryViev extends VerticalLayout {

    private ImageRepo imageUpader;

    @Autowired
    public GalleryViev(ImageRepo imageUpader) {
        this.imageUpader = imageUpader;
        List<Image> all = imageUpader.findAll();
        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAdress(), "empty");
            add(image);
        } );
    }
}