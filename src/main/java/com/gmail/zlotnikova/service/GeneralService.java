package com.gmail.zlotnikova.service;

import java.util.List;

public interface GeneralService<T> {

    T add(T t);

    List<T> findAll();

}