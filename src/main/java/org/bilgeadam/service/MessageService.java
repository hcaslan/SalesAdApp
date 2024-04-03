package org.bilgeadam.service;

import org.bilgeadam.repository.*;

public class MessageService {
    private CategoryRepository categoryRepository;
    private CommentRepository commentRepository;
    private FavouriteIlanRepository favouriteIlanRepository;
    private IlanRepository ilanRepository;
    private ImageRepository imageRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageService() {
        this.categoryRepository = new CategoryRepository();
        this.commentRepository = new CommentRepository();
        this.favouriteIlanRepository = new FavouriteIlanRepository();
        this.ilanRepository = new IlanRepository();
        this.imageRepository = new ImageRepository();
        this.messageRepository = new MessageRepository();
        this.userRepository = new UserRepository();
    }
}
