package by.itacademy.utils;


import by.itacademy.exceptions.ScriptNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sun.misc.IOUtils;

import java.io.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlQueryManager {

    public static synchronized String getSqlQuery(final String scriptName)  {
        InputStream is = SqlQueryManager.class.getClassLoader().getResourceAsStream("dbScripts/" + scriptName);
        try {
            return new String(IOUtils.readFully(is,Integer.MAX_VALUE,false));
        } catch (IOException e) {
            throw new ScriptNotFoundException(String.format("There is no script file in directory , file name [%s]", scriptName));
        }
    }
}
