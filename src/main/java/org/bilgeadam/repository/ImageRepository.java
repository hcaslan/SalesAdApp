package org.bilgeadam.repository;

import org.bilgeadam.entity.Image;

public class ImageRepository extends RepositoryManager<Image, Long> {
    public ImageRepository() {
        super(Image.class);
    }
}
