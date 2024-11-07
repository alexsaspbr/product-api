package tech.ada.products_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.products_api.dto.RegisterDTO;
import tech.ada.products_api.model.User;
import tech.ada.products_api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }


    public void salvar(RegisterDTO registerDTO) {

        String passwordEncrypted = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        User user = new User(registerDTO.getLogin(), passwordEncrypted, registerDTO.getRole());
        this.userRepository.save(user);

    }

}
