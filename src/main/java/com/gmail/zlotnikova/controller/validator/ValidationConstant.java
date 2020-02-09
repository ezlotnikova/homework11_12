package com.gmail.zlotnikova.controller.validator;

public interface ValidationConstant {

    int MIN_USERNAME_LENGTH = 1;
    int MAX_USERNAME_LENGTH = 40;
    String USERNAME_PATTERN = "[0-9a-zA-Z\\s]+";
    int MIN_PASSWORD_LENGTH = 8;
    int MAX_PASSWORD_LENGTH = 40;
    String PASSWORD_PATTERN = "[0-9a-zA-Z]+";

}