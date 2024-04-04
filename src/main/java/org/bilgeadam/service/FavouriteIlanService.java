package org.bilgeadam.service;

import org.bilgeadam.SessionContext;
import org.bilgeadam.entity.FavouriteIlan;
import org.bilgeadam.entity.Ilan;
import org.bilgeadam.enums.IlanStatus;
import org.bilgeadam.repository.FavouriteIlanRepository;
import org.bilgeadam.utility.InputHelper;

import java.util.Optional;

public class FavouriteIlanService {
    private FavouriteIlanRepository favouriteIlanRepository;


    private static IlanService ilanService = new IlanService();

    public FavouriteIlanService() {
        this.favouriteIlanRepository = new FavouriteIlanRepository();

    }

    public void addFavourite(Optional<Ilan> ilan) {
        if (ilan.isPresent()) {

            int choice = InputHelper.getIntegerInput("Favorilere Eklemek için 5'i Tuşlatıyın Bir üst menü için herhangi bir tuşlama yapın");
            if (choice == 5) {
                FavouriteIlan favouriteIlan = FavouriteIlan.builder().favouriteIlan_user_id(SessionContext.getUser()).favouriteIlan_ilan_id(ilan.get()).status(IlanStatus.ACTIVE).build();
                favouriteIlanRepository.save(favouriteIlan);
            }
        }
    }

    public void showFavouriteIlans() {
        favouriteIlanRepository.getAll().stream().filter(favouriteIlan -> (favouriteIlan.getStatus().equals(IlanStatus.ACTIVE)) && (favouriteIlan.getFavouriteIlan_user_id().getId().equals(SessionContext.getUser().getId())))
                .forEach(favouriteIlan -> {
                    ilanService.showIlanDetail(favouriteIlan.getFavouriteIlan_ilan_id().getId());
                });
    }

    public void deleteFavourite(Optional<Ilan> ilan) {
        favouriteIlanRepository.deleteById(ilan.get().getId());
    }

}
