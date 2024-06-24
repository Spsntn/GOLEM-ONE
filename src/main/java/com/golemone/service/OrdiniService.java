package com.golemone.service;

import com.golemone.exception.OrdineNotFoundException;
import com.golemone.model.*;
import com.golemone.repository.OrdiniRepository;
import com.golemone.repository.ProdottiRepository;
import com.golemone.repository.ScontriniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdiniService {

    @Autowired
    private OrdiniRepository ordiniRepository;

    @Autowired
    private ProdottiRepository prodottiRepository;
    @Autowired
    private ScontriniRepository scontriniRepository;

    public ResponseEntity<Ordini> saveOrdine(Ordini ordine) {
        return ResponseEntity.ok(ordiniRepository.save(ordine));
    }

    public ResponseEntity<Ordini> addOrderProduct(Ordini ordine) {

            Ordini newOrder = new Ordini(ordine.getProdotto(), ordine.getQty());
            CC cc = new CC(ordine.getProdotto().getId(), ordine.getId().getIdOrdine());
            ordine.setId(cc);
            return ResponseEntity.ok(ordiniRepository.save(ordine));

    }

    public Optional<Ordini> updateOrdine(CC id, Ordini updatedOrdine) {
        return ordiniRepository.findById(id).map(ordine -> {
            // Carica il prodotto dal database
            Prodotti prodotto = prodottiRepository.findById(updatedOrdine.getProdotto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Prodotto nel nuovo ordine non trovato"));


            Scontrini temp = scontriniRepository.findByOrdini_Id(id);

            if(temp!=null) {
                temp.getOrdini().remove(ordiniRepository.findById(id).get());
                scontriniRepository.save(temp);
            }

            // Rimuovi l'entità corrente dal contesto di persistenza
            ordiniRepository.delete(ordine);

            // Creare una nuova istanza di Ordini con la nuova chiave primaria
            CC newId = new CC(updatedOrdine.getProdotto().getId(), id.getIdOrdine());
            Ordini newOrdine = new Ordini();
            newOrdine.setId(newId);
            newOrdine.setQty(updatedOrdine.getQty());
            newOrdine.setProdotto(prodotto);

            // Salva la nuova entità
            ordiniRepository.save(newOrdine);
            if(temp!=null){
            temp.getOrdini().add(newOrdine);
            scontriniRepository.save(temp);
            }
            return newOrdine;
        });
    }

    public Ordini getOrdineById(UUID idProdotto, UUID idOrdine) {
        return ordiniRepository.findById(new CC(idProdotto, idOrdine)).orElseThrow(() -> new OrdineNotFoundException(new CC(idProdotto, idOrdine)));
    }

    public List<Ordini> getOrdineByOrdineUUID(UUID idOrdine) {
        List<Ordini> ordini = ordiniRepository.findAll();

        List<Ordini> specificOrdini = new ArrayList<>();
        for (Ordini o : ordini) {
            if (o.getId().getIdOrdine().equals(idOrdine)) {
                specificOrdini.add(o); // Aggiungi a specificOrdini invece che a ordini
            }
        }
        return specificOrdini;
    }


    public ResponseEntity<List<Ordini>> getAllOrdini() {
        return ResponseEntity.ok(ordiniRepository.findAll());
    }

    public ResponseEntity<Void> deleteOrdineById(UUID idProdotto, UUID idOrdine) {
        if(!ordiniRepository.existsById(new CC(idProdotto, idOrdine))) {
            throw new OrdineNotFoundException(new CC(idProdotto, idOrdine));
        }
        ordiniRepository.deleteById(new CC(idProdotto, idOrdine));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
