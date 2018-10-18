package by.itacademy.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlQueryPrinter {

    public static void printQuery(final PreparedStatement preparedStatement){
        System.out.println("\n-------------------------Executing query------------------------------");
        System.out.println(preparedStatement);
        System.out.println("----------------------------------------------------------------------\n");
    }
}
