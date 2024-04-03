package org.bilgeadam.repository;

import org.bilgeadam.entity.Comment;

public class CommentRepository extends RepositoryManager<Comment, Long> {
    public CommentRepository() {
        super(Comment.class);
    }
}
