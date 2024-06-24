package com.golemone.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CC implements Serializable {

    @Serial
    private static final long serialVersionUID = 6191513711424399753L;



    private UUID idProdotto;

    @GeneratedValue
    private UUID idOrdine;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CC cc = (CC) o;
        return Objects.equals(idProdotto, cc.idProdotto) && Objects.equals(idOrdine, cc.idOrdine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdotto, idOrdine);
    }
}
