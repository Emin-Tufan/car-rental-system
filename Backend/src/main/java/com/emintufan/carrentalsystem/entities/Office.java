package com.emintufan.carrentalsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@Table(name = "office")
@NoArgsConstructor
@Entity
public class Office {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "time")
    private String time;
}
