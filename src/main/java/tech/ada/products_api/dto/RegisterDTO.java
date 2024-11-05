package tech.ada.products_api.dto;

import lombok.Data;
import tech.ada.products_api.model.UserRole;

@Data
public class RegisterDTO {

    private String login;
    private String password;
    private UserRole role;

}
