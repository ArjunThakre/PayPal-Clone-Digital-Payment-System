package com.paypal.user_service.service;

import com.paypal.dto.CreateWalletRequest;
import com.paypal.user_service.client.WalletClient;
import com.paypal.user_service.entity.User;
import com.paypal.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final WalletClient walletClient;
    private UserRepository userRepository;

    public UserServiceImpl(WalletClient walletClient, UserRepository userRepository) {
        this.walletClient = walletClient;
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        User savedUser=userRepository.save(user);
        try {
            CreateWalletRequest request=new CreateWalletRequest();
            request.setUserId(savedUser.getId());
            request.setCurrency("INR");
            walletClient.createWallet(request);
        }catch (Exception ex) {
            userRepository.deleteById(savedUser.getId()); // rollback
            throw new RuntimeException("Wallet creation failed, user rolled back", ex);
        }
        return savedUser;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
