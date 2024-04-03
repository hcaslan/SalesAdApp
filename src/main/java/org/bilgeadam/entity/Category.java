package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblcategory")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne
    Category parentCategoryId;
    @OneToMany(mappedBy = "parentCategoryId", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Category> childsCategory;


    @OneToMany(mappedBy = "ilan_category_id", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Ilan> ilans;
    @Embedded
    private BaseEntity baseEntity;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
