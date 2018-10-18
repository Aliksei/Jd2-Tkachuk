package by.itacademy.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class DatabaseProperties {

    public static final String BASE_USER = PropertiesUtil.getProperty("database.user");
    public static final String BASE_USER_PASSWORD = PropertiesUtil.getProperty("database.pass");
    public static final String DATABASE_BASE_URL = PropertiesUtil.getProperty("database.url");
    public static final String DATABASE_INIT_SCRIPT = PropertiesUtil.getProperty("database.init.script");

}
