package com.musalasoft.dronemanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MEDICATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private int medicineId;

    @Column(name = "name")
    @NotBlank(message = "Medicine Name Can not be null or empty !")
    @Size(max = 200, message = "Medicine Name length can not exceed 200 character limit !")
    @Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "Invalid character in Medicine Name !")
    private String name;

    @Column(name = "weight")
    @NotBlank(message = "Medicine Weight Can not be null or empty !")
    @Size(message = "Medicine weight can not be Negative !")
    private float weight;

    @Column(name = "code")
    @NotBlank(message = "Medicine Code Can not be null or empty !")
    @Pattern(regexp = "^[A-Z0-9_-]*$", message = "Invalid character in medicine code !")
    @Size(max = 100, message = "Medicine Code length can not exceed 100 character limit !")
    private String code;

    @Column(name = "image")
    @NotBlank(message = "Medicine image URL Can not be null or empty !")
    @URL(message = "Invalid URL pattern !")
    private String imageUrl;
}
