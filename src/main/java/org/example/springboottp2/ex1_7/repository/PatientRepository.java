package org.example.springboottp2.ex1_7.repository;

import org.example.springboottp2.ex1_7.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<Patient, Long> {

}
