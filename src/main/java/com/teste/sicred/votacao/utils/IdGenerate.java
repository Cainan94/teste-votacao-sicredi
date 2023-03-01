package com.teste.sicred.votacao.utils;

import java.util.UUID;

public interface IdGenerate {
    static UUID getNew(){
        return UUID.randomUUID();
    }

    static UUID getFromString(String value){
        return UUID.fromString(value);
    }
}
