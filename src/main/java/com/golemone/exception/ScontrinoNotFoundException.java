package com.golemone.exception;

import java.util.UUID;

public class ScontrinoNotFoundException extends RuntimeException  {

    public ScontrinoNotFoundException(UUID id) {
        super("Scontrino not found with id: " + id);
    }
}
