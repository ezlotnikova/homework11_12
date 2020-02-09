package com.gmail.zlotnikova.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gmail.zlotnikova.controller.validator.ValidationConstant.MAX_PASSWORD_LENGTH;
import static com.gmail.zlotnikova.controller.validator.ValidationConstant.MAX_USERNAME_LENGTH;
import static com.gmail.zlotnikova.controller.validator.ValidationConstant.MIN_PASSWORD_LENGTH;
import static com.gmail.zlotnikova.controller.validator.ValidationConstant.MIN_USERNAME_LENGTH;
import static com.gmail.zlotnikova.controller.validator.ValidationConstant.PASSWORD_PATTERN;
import static com.gmail.zlotnikova.controller.validator.ValidationConstant.USERNAME_PATTERN;

public class ValidatorImpl implements Validator {

    private static Validator instance;

    private ValidatorImpl() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean validateUsername(String username) {
        return validateNotEmpty(username) &&
                validateLength(username, MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH) &&
                validatePattern(username, USERNAME_PATTERN);
    }

    @Override
    public boolean validatePassword(String password) {
        return validateNotEmpty(password) &&
                validateLength(password, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH) &&
                validatePattern(password, PASSWORD_PATTERN);
    }

    @Override
    public boolean validateRole(String roleName) {
        return validateNotEmpty(roleName);
    }

    private boolean validateNotEmpty(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

    private boolean validateLength(String parameter, int minParameterLength, int maxParameterLength) {
        return parameter.length() >= minParameterLength &&
                parameter.length() <= maxParameterLength;
    }

    private boolean validatePattern(String parameter, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(parameter);
        return matcher.matches();
    }

}