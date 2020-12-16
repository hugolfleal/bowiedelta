package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao extends GenericDao<Address>{


    public AddressDao() {
        super(Address.class);
    }
}
