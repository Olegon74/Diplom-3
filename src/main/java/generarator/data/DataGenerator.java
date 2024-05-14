package generarator.data;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerator {
    public static String RANDOM_EMAIL = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
    public static String RANDOM_PASS = RandomStringUtils.randomNumeric(6);
    public static String RANDOM_PASS_5_CHAR = RandomStringUtils.randomNumeric(4); //не валидный пароль из 4 цифр
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}



