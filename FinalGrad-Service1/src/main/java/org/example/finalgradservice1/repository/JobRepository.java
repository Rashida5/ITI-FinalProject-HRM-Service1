package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
