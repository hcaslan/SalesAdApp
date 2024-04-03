package org.bilgeadam.service;

import org.bilgeadam.entity.User;
import org.bilgeadam.repository.UserRepository;
import org.bilgeadam.utility.InputHelper;

import java.util.Optional;
import java.util.regex.Pattern;

public class UserService {
    InputHelper inputHelper = new InputHelper();

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public Optional<User> login() {
        User loggingUser = userRepository.findByColumnAndValue("username", inputHelper.stringInput("Lütfen username  girin ")).getFirst();
        if(loggingUser.getPassword().equals(inputHelper.stringInput("Lütfen password  girin")))
            return Optional.of(loggingUser);
        else
            return Optional.empty();
    }


    public void register() {
        String checkedEmail = emailControl();
        String checkedUsername = usernameControl();
        userRepository.save(User.builder()
                .email(checkedEmail)
                .username(checkedUsername)
                .password(inputHelper.stringInput("Lütfen password  girin"))
                .build());
    }

    private String usernameControl() {
        boolean isUsernameOk = false;
        String username = null;
        while (!isUsernameOk) {
            username = inputHelper.stringInput("Lütfen username  girin ");
            if (isUsernameUnique(username)) {
                isUsernameOk = true;
            }
        }
        return username;
    }

    private String emailControl() {
        boolean isMailOk = false;
        String email = null;
        while (!isMailOk) {
            email = inputHelper.stringInput("Lütfen email adresinizi girin ");
            if (isEmailValid(email) && isEmailUnique(email)) {
                isMailOk = true;
            }
        }
        return email;
    }

    private boolean isEmailValid(String email) {
        boolean matches = Pattern.matches("[_a-zA-Z0-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
        if (matches != true) {
            System.out.println("Hatali email yazimi");
        }
        return matches;
    }
    private boolean isEmailUnique(String email) {
        return userRepository.findByColumnAndValue("email", email).isEmpty();
    }

    private boolean isUsernameUnique(String userName) {
        return userRepository.findByColumnAndValue("username", userName).isEmpty();
    }

}
