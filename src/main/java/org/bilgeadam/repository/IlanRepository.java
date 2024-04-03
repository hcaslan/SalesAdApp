package org.bilgeadam.repository;

import org.bilgeadam.entity.Ilan;

public class IlanRepository extends RepositoryManager<Ilan, Long> {
    public IlanRepository() {
        super(Ilan.class);
    }
}
