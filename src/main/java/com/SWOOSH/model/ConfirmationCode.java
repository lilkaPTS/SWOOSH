package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "confirmation_codes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_code_id")
    private int confirmationCodeId;
    private String email;
    private String code;
}
