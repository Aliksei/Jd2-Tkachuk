package com.itacademy.utils;


import com.itacademy.exceptions.ScriptNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sun.misc.IOUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlQueryManager {

    public static synchronized String getSqlQuery(final String scriptName) {
        InputStream is = SqlQueryManager.class.getClassLoader().getResourceAsStream("dbScripts/" + scriptName);
        try {
            return new String(IOUtils.readFully(is, Integer.MAX_VALUE, false));
        } catch (IOException e) {
            throw new ScriptNotFoundException(String.format("There is no script file in directory , file name [%s]", scriptName));
        }
    }
}
