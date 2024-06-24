package com.golemone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordini")
public class Ordini {

    @EmbeddedId
    private CC id;

    @ManyToOne
    @MapsId("idProdotto")
    @JoinColumn(name = "idProdotto")
    private Prodotti prodotto;

    @Column(nullable = false)
    private int qty;

    public Ordini(Prodotti prodotto, int qty) {
        this.prodotto = prodotto;
        this.qty = qty;
    }
}
