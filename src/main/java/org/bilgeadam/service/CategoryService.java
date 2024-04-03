package org.bilgeadam.service;

import org.bilgeadam.entity.Category;
import org.bilgeadam.repository.CategoryRepository;
import org.bilgeadam.utility.InputHelper;
import org.bilgeadam.utility.OutputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryRepository categoryRepository;
    InputHelper inputHelper = new InputHelper();

    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }

    public List<Long> showParentlessCategory() {
        List<Long> parentlessCategoryIds = new ArrayList<Long>();
        categoryRepository.getAll().forEach(category -> {
            if (category.getParentCategoryId() == null) {
                System.out.println(category.getId() + " " + category.getName());
                parentlessCategoryIds.add(category.getId());
            }
        });
        return parentlessCategoryIds;
    }

    public Optional<Category> chooseParentlessCategory() {
        List<Long> parentCategoryIds = showParentlessCategory();
        Long parentCategoryChoice = inputHelper.getLongInput("Lütfen İlan vermek istediğiniz kategoriyi seçin");
        Optional<Category> choosenParentCategory;
        if(parentCategoryIds.stream().anyMatch(n -> n.equals(parentCategoryChoice))) {
            choosenParentCategory = categoryRepository.findById(parentCategoryChoice);
            return choosenParentCategory;
        } else {
            OutputHelper.errorMessage("Kategory Yok !");
            return chooseParentlessCategory();
        }
    }

    public Category getCategory() {
        Optional<Category> parentlessCategory = chooseParentlessCategory();
        if (parentlessCategory.isPresent()) {
            Optional<Category> category = chooseChildCategory(parentlessCategory);
            return category.orElseGet(parentlessCategory::get);
        } else {
            return parentlessCategory.get();
        }
    }

    public Optional<Category> chooseChildCategory( Optional<Category> parentCategory ) {
        List<Long> ids = new ArrayList<Long>();
        parentCategory.ifPresent(category ->category.getChildsCategory().forEach(child -> {
            System.out.println(child);
            ids.add(child.getId());
        }));
        Long choice = inputHelper.getLongInput("Lütfen İlan vermek istediğiniz kategoriyi seçin");
        Optional<Category> chosenCategory;
        if(ids.stream().anyMatch(n -> n.equals(choice))) {
            chosenCategory = categoryRepository.findById(choice);
            if(categoryRepository.getAll().stream().filter(category -> (category.getParentCategoryId() != null) && category.getParentCategoryId().getId().equals(chosenCategory.get().getId())).collect(Collectors.toList()).isEmpty()){
                return chosenCategory;
            }else{
                return chooseChildCategory(chosenCategory);
            }
        }else{
            OutputHelper.errorMessage("Kategory Yok !");
            return chooseChildCategory(parentCategory);
        }
    }
}
