package com.imedzenned.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "User name is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required ")
    private String email;
    private Instant created;
    private boolean enabled;



}
