package com.teste.sicred.votacao.utils;

import static org.apache.logging.log4j.util.Strings.EMPTY;

public interface Strings {
    static boolean isNullOrEmpty(String value){
        return value == null || value == EMPTY;
    }
}
