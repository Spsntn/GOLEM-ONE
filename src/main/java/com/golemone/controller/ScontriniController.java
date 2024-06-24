package com.golemone.controller;

import com.golemone.model.Scontrini;
import com.golemone.service.ScontriniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/scontrini")
public class ScontriniController {

    @Autowired
    private ScontriniService scontriniService;

    @GetMapping
    public ResponseEntity<List<Scontrini>> getAllScontrini() {
        List<Scontrini> scontrini = scontriniService.findAllScontrini();
        return new ResponseEntity<>(scontrini, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Scontrini> saveScontrino(@RequestBody Scontrini scontrino) {
        Scontrini cc = scontriniService.saveScontrino(scontrino);
        return new ResponseEntity<>(cc, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScontrino(@PathVariable("id") UUID id) {
        scontriniService.deleteScontrino(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Scontrini> updateScontrino(@PathVariable("id") UUID id, @RequestBody Scontrini scontrino) {
        return new ResponseEntity<>(scontriniService.updateScontrino(id, scontrino), HttpStatus.OK);
    }

}
