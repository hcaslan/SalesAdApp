package org.bilgeadam.service;

import org.bilgeadam.SessionContext;
import org.bilgeadam.entity.Ilan;
import org.bilgeadam.entity.Message;
import org.bilgeadam.repository.*;
import org.bilgeadam.utility.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageService {
    private MessageRepository messageRepository;

    public MessageService() {
        this.messageRepository = new MessageRepository();
    }

    public void sendMessage(Optional<Message> parent, Optional<Ilan> ilan) {
        String message = InputHelper.getStringInput("Mesajı Giriniz");
        Message message1;
        if(parent.isPresent()){
            message1 = Message.builder()
                    .message_sender_id(SessionContext.getUser())
                    .message_receiver_id(parent.get().getMessage_sender_id())
                    .parentMessage(parent.get())
                    .ilan(parent.get().getIlan())
                    .message(message)
                    .build();
        }else {
            message1 = Message.builder()
                    .message_sender_id(SessionContext.getUser())
                    .message_receiver_id(ilan.get().getIlan_user_id())
                    .ilan(ilan.get())
                    .message(message)
                    .build();
        }
        messageRepository.save(message1);
    }

    public void sendMessageByIlanMenu(Optional<Ilan> ilan){
        if (ilan.isPresent()) {
            int choice = InputHelper.getIntegerInput("Mesaj Gondemek Icin 5'i Tuşlayın Bir üst menü için herhangi bir tuşlama yapın");
            if (choice == 5) {
                sendMessage(Optional.empty(),ilan);
            }
        }
    }
    public List<Long> listParentlessMessages() {
        List<Long> parentlessMessageIds = new ArrayList<Long>();
        messageRepository.getParentlessMessages().forEach(message -> {
            System.out.println(message);
            parentlessMessageIds.add(message.getId());
        });
        return parentlessMessageIds;
    }
    public Optional<Message> select(List<Long> parentlessMessageIds){
        Long choice = InputHelper.getLongInput("Lütfen mesaj seçin");
        Optional<Message> message;
        if(parentlessMessageIds.stream().anyMatch(n -> n.equals(choice))){
            message = messageRepository.findById(choice);
            return message;
        }
        return Optional.empty();
    }



    public Message showMessageTree(Message message){
        Message messageTemp = message;
        while(messageTemp.getMessageList() != null){
            System.out.println(messageTemp.getId() + ":");
            System.out.println("Mesaj : " + messageTemp.getMessage());
                if(!messageTemp.getMessageList().isEmpty())
                    messageTemp = messageTemp.getMessageList().getFirst();
                else
                    break;
        }
        return messageTemp;
    }
}
