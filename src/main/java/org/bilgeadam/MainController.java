package org.bilgeadam;

import org.bilgeadam.entity.Category;
import org.bilgeadam.entity.Ilan;
import org.bilgeadam.entity.Message;
import org.bilgeadam.entity.User;
import org.bilgeadam.repository.CategoryRepository;
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


    public static void menu() {
        int choice;
        do {
            System.out.println("""
                    1)Register
                    2)login
                    0)Shotdown""");
            choice = InputHelper.getIntegerInput("Seçiminizi girin.");
            switch (choice) {
                case 1:
                    userService.register();
                    break;
                case 2:
                    Optional<User> login = userService.login();
                    if (login.isPresent()) {
                        SessionContext.setUser(login.get());
                        userMenu();
                        System.out.println("Giriş Başarılı");
                    } else {

                        System.out.println("Kullanıcı Adı ya da parola hatalı!");
                    }
                    break;
                default:
                    if (choice != 0) {
                        System.out.println("Hatalı Tuşlama yaptınız tekrar deneyin");
                    }
                    break;
            }


        } while (choice != 0);
    }

    private static void userMenu() {
        int choice;
        do {
            System.out.println("""
                    1)İlan ver
                    2)İlanları Listele
                    3)Favori İlanlarım
                    4)Gelen Kutusu
                    0)Güvenli çıkış""");
            choice = inputHelper.getIntegerInput("Seçiminizi girin.");
            switch (choice) {
                case 1:
                    ilanService.createIlan();
                    break;
                case 2:
                    ilanController();
                    break;
                case 3:
                    favoriIlanController();
                    break;
                case 4:
                    gelenKutusuKontroller();
                    break;
                default:
                    if (choice != 0) {
                        System.out.println("Hatalı Tuşlama yaptınız tekrar deneyin");
                    }
                    break;
            }


        } while (choice != 0);

    }

    public static void dummyData() {
//        Category teknoloji = Category.builder().name("Teknoloji").build();
//        Category bilgisayar = Category.builder().name("Bilgisayar").parentCategoryId(teknoloji).build();
//        Category laptop = Category.builder().name("Laptop").parentCategoryId(bilgisayar).build();
//        Category telefon = Category.builder().name("Telefon").parentCategoryId(teknoloji).build();
//
//        Category vasita = Category.builder().name("Vasıta").build();
//        Category araba = Category.builder().name("araba").parentCategoryId(vasita).build();
//        Category kamyon = Category.builder().name("kamyon").parentCategoryId(vasita).build();
//        new CategoryRepository().saveAll(List.of(teknoloji, bilgisayar, laptop, telefon, vasita, araba, kamyon));
        Category clothing = Category.builder().name("Clothing").build();
        Category menswear = Category.builder().name("Menswear").parentCategoryId(clothing).build();
        Category summerOutfit = Category.builder().name("SummerOutfit").parentCategoryId(menswear).build();
        Category swimTrunks = Category.builder().name("SwimTrunks").parentCategoryId(summerOutfit).build();
        new CategoryRepository().saveAll(List.of(clothing, menswear, summerOutfit, swimTrunks));
    }
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
