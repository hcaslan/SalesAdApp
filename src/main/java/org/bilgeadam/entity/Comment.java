package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.CommentStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblcomment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User comment_user_id;
    @ManyToOne
    @JoinColumn(name = "commenter_id")
    User comment_commenter_id;
    LocalDateTime commentDate;
    @Embedded
    private BaseEntity baseEntity;
    CommentStatus status;
}
