package pl.qubiak.photoHosting.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.qubiak.photoHosting.Model.Image;
import pl.qubiak.photoHosting.Repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;

    @Autowired
    public ImageUploader(ImageRepo imageRepo,
                       @Value("${cloudNameValue}") String cloudNameValue,
                       @Value("${apiKeyValue}") String apiKeyValue,
                       @Value("${apiSecretValue}") String apiSecretValue) {
        this.imageRepo = imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue));
    }

    public String uploadFileAndSaveToDb(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Data error. Try again");
        }
        return uploadResult.get("url").toString();
    }
}
