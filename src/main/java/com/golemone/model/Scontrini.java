package com.golemone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scontrini")
public class Scontrini {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "scontrino_ordine",
            joinColumns = {
                    @JoinColumn(name = "scontrino_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_prodotto", referencedColumnName = "idProdotto"),
                    @JoinColumn(name = "id_ordine", referencedColumnName = "idOrdine")
            }
    )
    private List<Ordini> ordini;


    @Column(nullable = false)
    private LocalDate data;

    // Costruttori, getter e setter


}
