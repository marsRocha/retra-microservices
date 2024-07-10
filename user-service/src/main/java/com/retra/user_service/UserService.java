package com.retra.user_service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow( () -> new IllegalStateException("user " + userId + " not found"));
    }

    public void createUser(UserPostDTO userPostDTO) {
        User user = User.builder()
                .firstName(userPostDTO.firstName())
                .lastName(userPostDTO.lastName())
                .email(userPostDTO.email())
                .password(userPostDTO.password())
                .dob(userPostDTO.dob())
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

    private UserGetDTO convertToDTO(User user) {
        return new UserGetDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDob()
        );
    }
}
