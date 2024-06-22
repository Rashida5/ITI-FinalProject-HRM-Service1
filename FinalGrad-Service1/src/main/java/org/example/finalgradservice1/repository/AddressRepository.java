package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Integer> {
}
