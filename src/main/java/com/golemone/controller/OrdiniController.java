package com.golemone.controller;


import com.golemone.model.CC;
import com.golemone.model.Ordini;
import com.golemone.service.OrdiniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordini")
public class OrdiniController {

    @Autowired
    private OrdiniService ordiniService;

    @GetMapping
    public ResponseEntity<List<Ordini>> getOrdini() {
        return ordiniService.getAllOrdini();
    }

    @PostMapping
    public ResponseEntity<Ordini> saveOrdine(@RequestBody Ordini ordine) {
        if(ordine.getId() != null && !ordiniService.getOrdineByOrdineUUID(ordine.getId().getIdOrdine()).isEmpty()) {
            return ordiniService.addOrderProduct(ordine);
        }
        CC cc = new CC(ordine.getProdotto().getId(), UUID.randomUUID());
        ordine.setId(cc);
        return ordiniService.saveOrdine(ordine);
    }

    @PutMapping("/{idProdotto}/{idOrdine}")
    public ResponseEntity<Ordini> updateOrdine(@PathVariable UUID idProdotto ,
                                               @PathVariable UUID idOrdine,
                                               @RequestBody Ordini updateOrdine) {
        if(ordiniService.getOrdineById(idProdotto, idOrdine) != null) {
            CC id = new CC(idProdotto, idOrdine);
            return ordiniService.updateOrdine(id, updateOrdine)
                    .map(ordine -> ResponseEntity.ok().body(ordine))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idProdotto}/{idOrdine}")
    public ResponseEntity<Void> deleteOrdine(@PathVariable UUID idProdotto,
                                             @PathVariable UUID idOrdine) {
        ordiniService.deleteOrdineById(idProdotto, idOrdine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
