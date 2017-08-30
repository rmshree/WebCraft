package app.web.services;

import app.web.domain.DTOs.ResponseDTO;

public interface LoginService {

    /**
     * @param inputPassword will be taken from the H2 database
     *                      and compared to what the user inputs
     */
    ResponseDTO logInUser(String username, String inputPassword, Boolean isWeb);

    ResponseDTO logOutUser(String username, Boolean isWeb);

}