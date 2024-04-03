package org.bilgeadam.repository;

import org.bilgeadam.entity.User;

public class UserRepository extends RepositoryManager<User, Long> {
    public UserRepository() {
        super(User.class);
    }
}
