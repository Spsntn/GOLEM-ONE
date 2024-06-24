package com.golemone.temp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;


@Getter
@Setter

public class Scontrini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;


    @Column(nullable = false)
    private double tot;

}
