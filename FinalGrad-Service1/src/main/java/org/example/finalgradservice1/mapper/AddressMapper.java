package org.example.finalgradservice1.mapper;

import org.example.finalgradservice1.dto.AddressDto;
import org.example.finalgradservice1.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

        public AddressDto toDTO(Address address) {
            AddressDto dto = new AddressDto();
            dto.setAddressId(address.getAddressId());
            dto.setStreet(address.getStreet());
            dto.setCity(address.getCity());
            dto.setState(address.getState());
            dto.setZipCode(address.getZipCode());
            dto.setCountry(address.getCountry());
            return dto;
        }

        public Address toEntity(AddressDto dto) {
            Address address = new Address();
            address.setAddressId(dto.getAddressId());
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            address.setZipCode(dto.getZipCode());
            address.setCountry(dto.getCountry());
            return address;
        }


}
