package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends GenericDao<User>{


    public UserDao() {
        super(User.class);
    }

    public String findByGoogleId(String googleId) {
        return (String) em.createQuery(
                "SELECT u.googleId FROM User u WHERE u.googleId LIKE ?1")
                .setParameter(1, googleId)
                .getSingleResult();
    }
    public User findUserByGoogleId(String googleId) {
        return (User) em.createQuery(
                "SELECT u FROM User u WHERE u.googleId LIKE ?1")
                .setParameter(1, googleId)
                .getSingleResult();
    }
}
