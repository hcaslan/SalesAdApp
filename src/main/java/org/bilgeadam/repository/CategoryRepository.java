package org.bilgeadam.repository;

import org.bilgeadam.entity.Category;

public class CategoryRepository extends RepositoryManager<Category, Long> {
    public CategoryRepository() {
        super(Category.class);
    }
}
