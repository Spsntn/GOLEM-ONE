package com.golemone.controller;


import com.golemone.model.Prodotti;
import com.golemone.service.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottiController {

    @Autowired
    private ProdottiService prodottiService;

    @GetMapping
    public ResponseEntity<List<Prodotti>> getAllProdotti() {
        return prodottiService.getAllProdotti();
    }

    @PostMapping
    public ResponseEntity<Prodotti> addProdotto(@RequestBody Prodotti prodotto) {
        System.out.println(prodotto.getNome_prodotto());
        Prodotti prod = prodottiService.saveProdotto(prodotto);
        return ResponseEntity.ok(prod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotti> updateProdotto(@PathVariable("id") UUID id, @RequestBody Prodotti prodotto) {
        return prodottiService.updateProdotto(id, prodotto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable("id") UUID id) {
        prodottiService.deleteProdottoById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
