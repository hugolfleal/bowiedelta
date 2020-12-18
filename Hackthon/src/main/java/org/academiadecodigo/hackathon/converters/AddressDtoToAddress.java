package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.AddressDto;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.academiadecodigo.hackathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddress extends AbstractConverter <AddressDto, Address>{

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Address convert(AddressDto addressDto) {

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        address.setId(addressDto.getId());
        address.setUser(userService.get(addressDto.getUserId()));

        return address;
    }
}
