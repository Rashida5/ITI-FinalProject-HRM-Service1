package org.example.finalgradservice1.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressDto {

    private Integer addressId;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Zip code cannot be blank")
    private String zipCode;

    @NotBlank(message = "Country cannot be blank")
    private String country;

}