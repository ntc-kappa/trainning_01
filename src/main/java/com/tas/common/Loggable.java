package com.tas.common;

import org.apache.log4j.Logger;


public interface Loggable {
    default Logger getLogger() {
        return Logger.getLogger(this.getClass());
    };

    default Logger getLogger(String name) {
         return Logger.getLogger(name);
    }
}
