package org.example.finalgradservice1.controller;

import org.example.finalgradservice1.dto.AddressDto;
import org.example.finalgradservice1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Integer id) {
        AddressDto addressDto = addressService.getAddressById(id);
        return addressDto != null ?
                ResponseEntity.ok(addressDto) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> saveAddress(@RequestBody AddressDto addressDto) {
        boolean saved = addressService.saveAddress(addressDto);
        return saved ? ResponseEntity.ok("Address saved successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save address");
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
        boolean updated = addressService.updateAddress(addressDto);
        return updated ? ResponseEntity.ok("Address updated successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        boolean deleted = addressService.deleteAddress(id);
        return deleted ? ResponseEntity.ok("Address deleted successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
    }
}