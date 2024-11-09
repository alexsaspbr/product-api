package tech.ada.products_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.products_api.dto.LoginResponseDTO;
import tech.ada.products_api.dto.RegisterDTO;
import tech.ada.products_api.dto.UserDTO;
import tech.ada.products_api.exception.TokenInvalidException;
import tech.ada.products_api.model.User;
import tech.ada.products_api.service.TokenService;
import tech.ada.products_api.service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) throws TokenInvalidException {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDTO.getLogin(),
                userDTO.getPassword());
        var authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        String token = this.tokenService.generatedToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {

        if(Objects.nonNull(this.userService.findByLogin(registerDTO.getLogin()))) {
            return ResponseEntity.badRequest().build();
        }

        this.userService.salvar(registerDTO);
        return ResponseEntity.ok("Usuario registrado");

    }

}
