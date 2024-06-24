package com.golemone.exception;

import java.util.UUID;

public class ProdottiNotFoundException extends RuntimeException  {

    public ProdottiNotFoundException(UUID id) {
        super("Menu not found with id: " + id);
    }
}
