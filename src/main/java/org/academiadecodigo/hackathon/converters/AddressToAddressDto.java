package org.academiadecodigo.hackathon.converters;

import org.academiadecodigo.hackathon.command.AddressDto;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDto extends AbstractConverter <Address, AddressDto>{


    @Override
    public AddressDto convert(Address address) {

        AddressDto addressDto = new AddressDto();

        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setDistrict(address.getDistrict());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setId(address.getId());
        addressDto.setUserId(address.getUser().getId());

        return addressDto;
    }

}
