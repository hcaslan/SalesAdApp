package org.bilgeadam;

import org.bilgeadam.entity.Ilan;
import org.bilgeadam.entity.Message;
import org.bilgeadam.repository.MessageRepository;
import org.bilgeadam.service.FavouriteIlanService;
import org.bilgeadam.service.IlanService;
import org.bilgeadam.service.MessageService;
import org.bilgeadam.service.UserService;
import org.bilgeadam.utility.InputHelper;

import java.util.List;
import java.util.Optional;

public class MainController {
    static InputHelper inputHelper = new InputHelper();
    static UserService userService = new UserService();
    static IlanService ilanService = new IlanService();
    static MessageService messageService = new MessageService();

    static MessageRepository messageRepository = new MessageRepository();
    static FavouriteIlanService favouriteIlanService = new FavouriteIlanService();
    public static void ilanController(){
        ilanService.showAllIlan();
        Optional<Ilan> ilan = ilanService.showIlanDetail(InputHelper.getLongInput("Lütfen detayını görmek istediğiniz ilanın id'sini girin"));
        favouriteIlanService.addFavourite(ilan);
        messageService.sendMessageByIlanMenu(ilan);
    }

    public static void favoriIlanController(){
        favouriteIlanService.showFavouriteIlans();
        Optional<Ilan> favoriteIlan = ilanService.showIlanDetail(InputHelper.getLongInput("Lütfen detayını görmek istediğiniz ilanın id'sini girin"));
        favouriteIlanService.deleteFavourite(favoriteIlan);
    }
    public  static void gelenKutusuKontroller(){
        List<Long> parentlessMessageIds = messageService.listParentlessMessages();
        Optional<Message> selected = messageService.select(parentlessMessageIds);
        Message message = messageService.showMessageTree(selected.get());
        messageService.sendMessage(Optional.of(message),Optional.empty());
    }
}
