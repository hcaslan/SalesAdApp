package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.ImageStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblimage")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ilan_id")
    Ilan image_ilan_id;
    String image_url;
    ImageStatus status;
    @Embedded
    private BaseEntity baseEntity;

    @Override
    public String toString() {
        return "Image{" +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
