package org.bilgeadam.service;

import org.bilgeadam.entity.Ilan;
import org.bilgeadam.entity.Image;
import org.bilgeadam.enums.ImageStatus;
import org.bilgeadam.repository.ImageRepository;
import org.bilgeadam.utility.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class ImageService {

    private ImageRepository imageRepository;
    InputHelper inputHelper = new InputHelper();

    public ImageService() {
        this.imageRepository = new ImageRepository();
    }

    public List<Image> saveIlanImages(Ilan ilan) {
        List<Image> images = new ArrayList<>();
        Image image;
        for (int i = 0; i < 10; i++) {
            String imageString = inputHelper.stringInput("Lütfen ilana ait fotoğraf girin");
            if (!(imageString).equals("0")) {
                image = Image.builder().image_ilan_id(ilan).status(ImageStatus.ACTIVE).image_url(imageString).build();
                images.add(image);
                imageRepository.save(image);
            } else {
                break;
            }
        }
        return images;
    }
}
