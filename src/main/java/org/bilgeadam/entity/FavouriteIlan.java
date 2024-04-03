package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.IlanStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblfavouriteilan")
public class FavouriteIlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User favouriteIlan_user_id;
    @ManyToOne
    Ilan favouriteIlan_ilan_id; //Tek taraflı ilişki kurduk
    @Embedded
    private BaseEntity baseEntity;
    IlanStatus status;

    @Override
    public String toString() {
        return "FavouriteIlan{" +
                "id=" + id +
                ", favouriteIlan_user_id=" + favouriteIlan_user_id +
                ", favouriteIlan_ilan_id=" + favouriteIlan_ilan_id +
                ", baseEntity=" + baseEntity +
                ", status=" + status +
                '}';
    }
}
