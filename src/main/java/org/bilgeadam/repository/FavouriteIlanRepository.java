package org.bilgeadam.repository;

import org.bilgeadam.entity.FavouriteIlan;

public class FavouriteIlanRepository extends RepositoryManager<FavouriteIlan, Long> {
    public FavouriteIlanRepository() {
        super(FavouriteIlan.class);
    }

}
