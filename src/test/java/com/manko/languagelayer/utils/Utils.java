package com.manko.languagelayer.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public final class Utils {

    public static String readResource(String filePath) {
        try {
            return IOUtils.toString(Utils.class.getResourceAsStream(filePath), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}