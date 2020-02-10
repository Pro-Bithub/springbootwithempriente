package com.demo.dao;

import com.demo.model.Empriente;
import org.hibernate.validator.constraints.EAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmprienteRepository extends JpaRepository<Empriente,String> {


    @Query("select e from Empriente e ")
    List<String>getFingrs();
    Optional<Empriente> findByFingr(Long oneCustomerOrder);

}
