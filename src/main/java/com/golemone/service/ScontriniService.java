package com.golemone.service;

import com.golemone.exception.ScontrinoNotFoundException;
import com.golemone.model.Ordini;
import com.golemone.model.Scontrini;
import com.golemone.repository.OrdiniRepository;
import com.golemone.repository.ScontriniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ScontriniService {

    @Autowired
    private ScontriniRepository scontriniRepository;

    @Autowired
    private OrdiniService ordiniService;


    public Scontrini saveScontrino(Scontrini scontrino) {
        if(scontrino.getOrdini()!= null && !scontrino.getOrdini().isEmpty()) {
            UUID OrdineId = scontrino.getOrdini().get(0).getId().getIdOrdine();
            List<Ordini> ordini = ordiniService.getOrdineByOrdineUUID(OrdineId);
            scontrino.setOrdini(ordini);
            scontrino.setData(java.time.LocalDate.now());
            return scontriniRepository.save(scontrino);
        }else{
            throw new IllegalArgumentException("Lo scontrino deve contenere almeno un ordine");
        }
    }

    public List<Scontrini> findAllScontrini() {
        return scontriniRepository.findAll();
    }

    public Scontrini getScontrinoById(UUID id) {

        return scontriniRepository.findById(id).orElseThrow(() -> new ScontrinoNotFoundException(id));
    }

    public Scontrini updateScontrino(UUID id, Scontrini scontrino) {
        if(getScontrinoById(id)!= null) {
            if(scontrino.getOrdini()!= null) {
                Scontrini newScontrino = getScontrinoById(id);
                UUID OrdineId = scontrino.getOrdini().get(0).getId().getIdOrdine();
                List<Ordini> ordini = ordiniService.getOrdineByOrdineUUID(OrdineId);
                newScontrino.setOrdini(ordini);
                if(scontrino.getData() != null && scontrino.getData() != newScontrino.getData()) {
                    newScontrino.setData(scontrino.getData());
                }
                return scontriniRepository.save(newScontrino);
            }else{
                throw new IllegalArgumentException("Lo scontrino deve contenere almeno un ordine");
            }
        }else{
            throw new ScontrinoNotFoundException(id);
        }
    }

    public void deleteScontrino(UUID id) {
        if(!scontriniRepository.existsById(id)) {
            throw new ScontrinoNotFoundException(id);
        }
        scontriniRepository.deleteById(id);
    }

}
