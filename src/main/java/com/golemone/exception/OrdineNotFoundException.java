package com.golemone.exception;

import com.golemone.model.CC;

public class OrdineNotFoundException extends RuntimeException  {

    public OrdineNotFoundException(CC id) {
        super("Ordine not found with id: " + id);
    }

}
