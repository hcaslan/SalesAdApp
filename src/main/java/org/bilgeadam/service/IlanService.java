package org.bilgeadam.service;

import org.bilgeadam.SessionContext;
import org.bilgeadam.entity.Category;
import org.bilgeadam.entity.Ilan;
import org.bilgeadam.entity.Image;
import org.bilgeadam.enums.IlanStatus;
import org.bilgeadam.repository.IlanRepository;
import org.bilgeadam.utility.InputHelper;

import java.util.List;
import java.util.Optional;


public class IlanService {
    InputHelper inputHelper = new InputHelper();
    static CategoryService categoryService = new CategoryService();
    static ImageService imageService = new ImageService();
    FavouriteIlanService favouriteIlanService = new FavouriteIlanService();
    private IlanRepository ilanRepository;

    public IlanService() {
        this.ilanRepository = new IlanRepository();
    }

    public void createIlan() {
        Category optionalCategory = categoryService.getCategory();
        Category category = optionalCategory;

        String ilanTitle = InputHelper.getStringInput("Lütfen ilanın başlığını yazın");
        String description = InputHelper.getStringInput("Lütfen ilanın açıklamasını girin");
        Double ilanPrice = InputHelper.getDoubleInput("Lütfen ilanın fiyatını girin");
        String location = InputHelper.getStringInput("Lütfen bulunduğunuz ilçe yazın");

        Ilan ilan = Ilan.builder()
                .ilan_user_id(SessionContext.getUser())
                .ilan_category_id(category)
                .title(ilanTitle)
                .description(description)
                .location(location)
                .status(IlanStatus.ACTIVE)
                .price(ilanPrice).build();
        ilanRepository.save(ilan);
        List<Image> images = imageService.saveIlanImages(ilan);
        ilan.setImages(images);
    }

    public void showAllIlan() {
        ilanRepository.getAll().stream().forEach(ilan -> {
            System.out.println("ID " + ilan.getId() + " Başlık " + ilan.getTitle() + " Açıklama " + ilan.getDescription() + " fiyat " + ilan.getPrice());
        });
    }

    public Optional<Ilan> showIlanDetail(Long choosenIlanId) {
        Optional<Ilan> choosenIlan = ilanRepository.findById(choosenIlanId);
        if (choosenIlan.isPresent()) {

            System.out.println(choosenIlan.get().getId());
            System.out.println(choosenIlan.get().getTitle());
            System.out.println("Fiyat : " + choosenIlan.get().getPrice() + "TL");
            System.out.println(choosenIlan.get().getIlan_user_id().getUsername());
            System.out.println(choosenIlan.get().getDescription());
            System.out.println(choosenIlan.get().getImages());
            return choosenIlan;
        } else {
            System.out.println("Aradığınız ilan bulunamamıştır");
        }
        return Optional.empty();
    }
}
