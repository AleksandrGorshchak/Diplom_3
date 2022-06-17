package com.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.RandomStringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoForCreateNewUser {

    public String email;
    public String password;
    public String name;

    public InfoForCreateNewUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static InfoForCreateNewUser getRandomWithCorrectPassword() {

        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        String passwordCorrectWithSixCharacters = RandomStringUtils.randomAlphabetic(6);
        return new InfoForCreateNewUser(email, passwordCorrectWithSixCharacters, name);
    }

    public static InfoForCreateNewUser getRandomWithNotCorrectPassword() {

        String name = RandomStringUtils.randomAlphabetic(10);
        String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        String passwordUnCorrectWithFiveCharacters = RandomStringUtils.randomAlphabetic(5);
        return new InfoForCreateNewUser(email, passwordUnCorrectWithFiveCharacters, name);
    }
}