package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.UserStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, nullable = false)
    String username;
    String password;
    @Column(unique = true, nullable = false)
    String email;
    String tel;
    String profileImageUrl;
    String konum;
    UserStatus status;
    @OneToMany(mappedBy = "message_sender_id", cascade = CascadeType.PERSIST)
    List<Message> sendMessages;
    @OneToMany(mappedBy = "message_receiver_id", cascade = CascadeType.PERSIST)
    List<Message> reveivedMessages;
    @OneToMany(mappedBy = "comment_user_id", cascade = CascadeType.PERSIST)
    List<Comment> receiveComments;
    @OneToMany(mappedBy = "comment_commenter_id", cascade = CascadeType.PERSIST)
    List<Comment> sendComments;
    @OneToMany(mappedBy = "ilan_user_id", cascade = CascadeType.PERSIST)
    List<Ilan> ilans;
    @OneToMany(mappedBy = "favouriteIlan_user_id", cascade = CascadeType.PERSIST)
    List<FavouriteIlan> favouriteIlans;


    @Embedded
    private BaseEntity baseEntity;
}
