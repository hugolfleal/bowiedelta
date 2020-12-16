package org.academiadecodigo.hackathon.services;


import org.academiadecodigo.hackathon.persistence.dao.AddressDao;
import org.academiadecodigo.hackathon.persistence.dao.UserDao;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private AddressDao addressDao;
    private UserDao userDao;

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Address get(Integer id) {
        return addressDao.findById(id);
    }

    @Transactional
    public Address save(Address address) {
        return addressDao.saveOrUpdate(address);
    }

    @Transactional
    public void delete(Integer id) {
        addressDao.delete(id);
    }

    public List<Address> list() {
        return addressDao.findAll();
    }


}
