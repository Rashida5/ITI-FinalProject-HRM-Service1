package org.example.finalgradservice1.service;

import org.example.finalgradservice1.dto.AddressDto;
import org.example.finalgradservice1.mapper.AddressMapper;
import org.example.finalgradservice1.model.Address;
import org.example.finalgradservice1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public AddressDto getAddressById(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        return address != null ? addressMapper.toDTO(address) : null;
    }

    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean saveAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);
        Address savedAddress = addressRepository.save(address);
        return savedAddress != null && savedAddress.getAddressId() != null;
    }

    public boolean updateAddress(AddressDto addressDto) {
        Address existingAddress = addressRepository.findById(addressDto.getAddressId()).orElse(null);
        if (existingAddress != null) {
            Address updatedAddress = addressMapper.toEntity(addressDto);
            addressRepository.save(updatedAddress);
            return true;
        }
        return false;
    }

    public boolean deleteAddress(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            addressRepository.delete(address);
            return true;
        }
        return false;
    }
}