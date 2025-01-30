package com.ljairgt15.devops_tcs.repository;

import com.ljairgt15.devops_tcs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email like %:correo%")
    List<User> findUsuariosByCorreo(@Param("correo") String correo);



}
