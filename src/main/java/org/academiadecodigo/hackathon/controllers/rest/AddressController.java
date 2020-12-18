package org.academiadecodigo.hackathon.controllers.rest;

import org.academiadecodigo.hackathon.command.AddressDto;
import org.academiadecodigo.hackathon.converters.AddressDtoToAddress;
import org.academiadecodigo.hackathon.converters.AddressToAddressDto;
import org.academiadecodigo.hackathon.persistence.model.Address;
import org.academiadecodigo.hackathon.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressToAddressDto addressToAddressDto;
    private AddressService addressService;
    private AddressDtoToAddress addressDtoToAddress;

    @Autowired
    public void setAddressToAddressDto(AddressToAddressDto addressToAddressDto) {
        this.addressToAddressDto = addressToAddressDto;
    }

    @Autowired
    public void setAddressDtoToAddress(AddressDtoToAddress addressDtoToAddress) {
        this.addressDtoToAddress = addressDtoToAddress;
    }

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }



    @RequestMapping(method = RequestMethod.GET, path = "/{uid}/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Integer id, @PathVariable Integer uid){

        Address address = addressService.get(id);
        AddressDto addressDto = addressToAddressDto.convert(address);


        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{uid}/create")
    public ResponseEntity<AddressDto> createAddress(@PathVariable Integer uid,
                                                @Valid @RequestBody AddressDto addressDto,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        addressDto.setUserId(uid);
        Address address = addressDtoToAddress.convert(addressDto);
        Address savedAddress = addressService.save(address);

        addressDto =addressToAddressDto.convert(savedAddress);

        return new ResponseEntity<>(addressDto, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
    public ResponseEntity<AddressDto> editAddress(@Valid @RequestBody AddressDto addressDto,
                                            BindingResult bindingResult,
                                            @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Address address = addressService.get(id);
            address.setCity(addressDto.getCity());
            address.setCountry(addressDto.getCountry());
            address.setDistrict(addressDto.getDistrict());
            address.setStreet(addressDto.getStreet());
            address.setZipCode(addressDto.getZipCode());

            Address savedAddress = addressService.save(address);
            addressDto = addressToAddressDto.convert(savedAddress);
            return new ResponseEntity<>(addressDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id) {
        try {
            addressService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
