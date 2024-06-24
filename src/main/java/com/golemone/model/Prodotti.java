package com.golemone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotti")
public class Prodotti {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(nullable = false)
    private String nome_prodotto;


    @Column(nullable = false)
    private double prezzo;


}
