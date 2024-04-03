package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.MessageStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblmessage")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    User message_sender_id;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    User message_receiver_id;
    MessageStatus status;
    @Embedded
    private BaseEntity baseEntity;
}
