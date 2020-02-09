package com.gmail.zlotnikova.service;

import com.gmail.zlotnikova.service.model.UserDTO;

public interface UserService extends GeneralService<UserDTO> {

    UserDTO findUser(String username, String password);

}