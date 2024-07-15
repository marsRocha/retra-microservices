package com.retra.user_service.service;

import com.retra.user_service.dto.UserSetDTO;
import com.retra.user_service.model.User;
import com.retra.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND = "User %d not found";

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalStateException(String.format(USER_NOT_FOUND, userId)));
    }

    public void createUser(UserSetDTO userSetDTO) {
        User user = User.builder()
                .firstName(userSetDTO.firstName())
                .lastName(userSetDTO.lastName())
                .email(userSetDTO.email())
                .password(userSetDTO.password())
                .dob(userSetDTO.dob())
                .build();

        /*
        // todo: check if email valid
        // todo: check if email not taken
        userRepository.saveAndFlush(user);

        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
        */
        userRepository.save(user);
    }

    @Transactional
    private void editUser(UserSetDTO userSetDTO, Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(USER_NOT_FOUND, userId)));

        if(userSetDTO.firstName() != null && !userSetDTO.firstName().isEmpty()) {
            u.setFirstName(userSetDTO.firstName());
        }
        if(userSetDTO.lastName() != null && !userSetDTO.lastName().isEmpty()) {
            u.setLastName(userSetDTO.lastName());
        }
        if(userSetDTO.email() != null && !userSetDTO.email().isEmpty()) {
            u.setEmail(userSetDTO.email());
        }
        if(userSetDTO.password() != null && !userSetDTO.password().isEmpty()) {
            u.setPassword(userSetDTO.password());
        }
        if(userSetDTO.dob() != null) {
            u.setDob(userSetDTO.dob());
        }
    }

    private void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new IllegalStateException(String.format(USER_NOT_FOUND, userId));
        }
        userRepository.deleteById(userId);
    }
}
