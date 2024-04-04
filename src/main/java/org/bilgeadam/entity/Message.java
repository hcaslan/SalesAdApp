package org.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bilgeadam.enums.MessageStatus;

import java.util.List;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    User message_sender_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    User message_receiver_id;

    MessageStatus status;

    String message;

    @ManyToOne(fetch = FetchType.EAGER)
    Message parentMessage;

    @OneToMany(mappedBy = "parentMessage", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Message> messageList;

    @Embedded
    private BaseEntity baseEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ilan_id")
    private Ilan ilan;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message_sender_id=" + message_sender_id.getUsername() +
                ", message_receiver_id=" + message_receiver_id.getUsername() +
                ", title=" + ilan.getTitle() +
                '}';
    }
}
