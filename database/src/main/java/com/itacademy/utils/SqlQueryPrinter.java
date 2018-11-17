package com.itacademy.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlQueryPrinter {

    public static void printQuery(final String preparedStatement) {
        System.out.println("\n-------------------------Executing query------------------------------");
        System.out.println(preparedStatement);
        System.out.println("----------------------------------------------------------------------\n");
    }
}
