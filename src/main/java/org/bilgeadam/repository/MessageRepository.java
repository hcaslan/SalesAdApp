package org.bilgeadam.repository;

import org.bilgeadam.entity.Message;

public class MessageRepository extends RepositoryManager<Message, Long> {
    public MessageRepository() {
        super(Message.class);
    }
}
