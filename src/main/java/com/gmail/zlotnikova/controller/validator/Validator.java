package com.gmail.zlotnikova.controller.validator;

public interface Validator {

    boolean validateUsername(String username);

    boolean validatePassword(String password);

    boolean validateRole(String password);

}