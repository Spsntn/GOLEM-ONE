package com.golemone.service;

import com.golemone.exception.ProdottiNotFoundException;
import com.golemone.model.Prodotti;
import com.golemone.repository.ProdottiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdottiService {

    @Autowired
    private ProdottiRepository prodottiRepository;

    public Prodotti saveProdotto(Prodotti prodotto) {

        if (prodotto.getNome_prodotto() == null) {
            throw new IllegalArgumentException("Il nome del prodotto non può essere nullo.");
        }
        // Salvataggio dell'oggetto nel repository
       return prodottiRepository.save(prodotto);

    }

    public ResponseEntity<Prodotti> updateProdotto(UUID id, Prodotti updatedProdotto) {
        prodottiRepository.findById(id).orElseThrow(() -> new ProdottiNotFoundException(id));
        return prodottiRepository.findById(id)
                .map(prodotto -> {
                    if(!prodotto.getNome_prodotto().equals(updatedProdotto.getNome_prodotto()) && updatedProdotto.getNome_prodotto()!=null && !updatedProdotto.getNome_prodotto().equals(""))
                        prodotto.setNome_prodotto(updatedProdotto.getNome_prodotto());
                    if(prodotto.getPrezzo()!=updatedProdotto.getPrezzo() && updatedProdotto.getPrezzo()!=0.0)
                        prodotto.setPrezzo(updatedProdotto.getPrezzo());
                    return prodottiRepository.save(prodotto);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Prodotti> getProdottoById(UUID id) {
        return prodottiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProdottiNotFoundException(id));
    }

    public ResponseEntity<List<Prodotti>> getAllProdotti() {
        return ResponseEntity.ok(prodottiRepository.findAll());
    }

    public ResponseEntity<Void> deleteProdottoById(UUID id) {
        prodottiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
