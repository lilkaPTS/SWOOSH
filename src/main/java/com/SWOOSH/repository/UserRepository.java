package com.SWOOSH.repository;

import com.SWOOSH.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u WHERE u.email = :email AND u.status = 'ACTIVE'")
    User findByEmail(@Param("email") String email);

    @Query("SELECT case when count(u)>0 then TRUE else FALSE end FROM User u "
            + "WHERE u.email = :email AND u.status = 'ACTIVE'")
    Boolean existUserByEmail(@Param("email") String email);

    @Query("FROM User u WHERE u.status = 'ACTIVE'")
    List<User> findAllUsers();
}
