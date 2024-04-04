package org.bilgeadam.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.bilgeadam.SessionContext;
import org.bilgeadam.entity.Message;
import org.bilgeadam.entity.User;

import java.util.List;

public class MessageRepository extends RepositoryManager<Message, Long> {
    public MessageRepository() {
        super(Message.class);
    }

    public List<Message> getParentlessMessages(){
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Message> cq = cb.createQuery(Message.class);
            Root<Message> root = cq.from(Message.class);
            Join<Message, User> senderJoin = root.join("message_sender_id");
            Join<Message, User> receiverJoin = root.join("message_receiver_id");
            cq.select(root).where(cb.and(cb.or(
                    cb.equal(senderJoin, SessionContext.getUser()),
                    cb.equal(receiverJoin, SessionContext.getUser())
            )),cb.isNull(root.get("parentMessage")));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}
