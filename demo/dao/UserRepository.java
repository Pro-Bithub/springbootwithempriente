package com.demo.dao;

import com.demo.model.Device;
import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User ,Long> {
/*
    @Query("select distinct cin from user  where cin = :cin  ")
    Optional<User>getCind(@Param("cin")Long cin);

*/
@Query("select distinct cin from user  where cin = :cin  ")
Optional<User>findFirstByCin(@Param("cin")Long cin);
/* or
@Query("select  cin from user  where cin = ?1  ") 
Optional<User>findFirstByCin(Long cin);*/

}
