package com.SWOOSH.repository;

import com.SWOOSH.model.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Integer> {
    Optional<ConfirmationCode> getConfirmationCodeByEmail(String email);
    int countByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "UPDATE confirmation_codes SET code = :code WHERE email = :email", nativeQuery = true)
    void updateCode(@Param("email") String email, @Param("code") String code);
}
