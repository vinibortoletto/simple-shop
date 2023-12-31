package com.vinibortoletto.simpleshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_customer")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    private String name;
    private String email;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "tb_customer_address",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Cart cart;
}