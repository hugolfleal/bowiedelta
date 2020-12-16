package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends GenericDao<User>{


    public UserDao() {
        super(User.class);
    }
}
