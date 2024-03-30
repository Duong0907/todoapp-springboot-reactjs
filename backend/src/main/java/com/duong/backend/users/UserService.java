package com.duong.backend.users;

import com.duong.backend.users.requests.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        return users;
    }

    public User getUserById(Integer id) throws Exception {
        Optional<User> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public User updateUser(Integer userId, UpdateUserRequest request) throws Exception {
        if (repository.existsById(userId)) {
            User user = repository.findById(userId).get();
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setEmail(request.getEmail());
            repository.save(user);

            return user;

        } else {
            throw new Exception("User not found");
        }
    }

    public void deleteUserById(Integer userId) throws Exception {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
        } else {
            throw new Exception("User not found");
        }
    }
}
