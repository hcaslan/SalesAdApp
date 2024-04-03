package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.IlanStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblilan")
public class Ilan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User ilan_user_id;
    @ManyToOne
    Category ilan_category_id;
    @OneToMany(mappedBy = "image_ilan_id", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    List<Image> images;
    String title;
    String description;
    String location;
    Double price;
    IlanStatus status;
    @Embedded
    private BaseEntity baseEntity;

}
