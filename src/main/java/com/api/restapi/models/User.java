package com.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AuditModel {

    @NotNull
    @Email
    @Size(max = 100)
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
//    @JsonProperty("Mail Id")
    @Column(name = "email")
    String email;

    @NotNull
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @JsonIgnoreProperties("password")
    @Column(name = "password")
    String password;
}
