package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.UserDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User save(UserDto dto) {
        Optional<User> user = repository.findByEmail(dto.email());

        if (user.isPresent()) {
            throw new ConflictException("Email already exists in database");
        }

        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);
        return repository.save(newUser);
    }

    public User update(UserDto dto, String id) {
        User user = findById(id);
        BeanUtils.copyProperties(dto, user);
        return repository.save(user);
    }

    public void delete(String id) {
        findById(id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void saveUserAddress(Address address, String userId) {
        User user = findById(userId);
        user.getAddresses().add(address);
        repository.save(user);
    }
}
