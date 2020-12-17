package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.AddressDto;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddress extends AbstractConverter <AddressDto, Address>{


    @Override
    public Address convert(AddressDto addressDto) {

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        address.setId(addressDto.getId());

        return address;
    }
}
