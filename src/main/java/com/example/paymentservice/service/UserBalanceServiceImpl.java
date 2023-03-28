package com.example.paymentservice.service;

import com.example.paymentservice.entity.UserBalance;
import com.example.paymentservice.exception.UserBalanceNotFoundException;
import com.example.paymentservice.repository.UserBalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImpl implements UserBalanceService{

    private final UserBalanceRepository userBalanceRepository;

    public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository) {
        this.userBalanceRepository = userBalanceRepository;
    }

    @Override
    public int getUserBalance(String userId) {
        return userBalanceRepository.findByUserId(userId)
                .orElseThrow(UserBalanceNotFoundException::new)
                .getBalance();
    }

    @Override
    public void updateBalance(String userId, int cost) {

        UserBalance userBalance = userBalanceRepository.findByUserId(userId)
                .orElseThrow(UserBalanceNotFoundException::new);

        userBalance.setBalance(userBalance.getBalance() + cost);
        userBalanceRepository.save(userBalance);
    }

    @Override
    public boolean hasEnoughMoney(String userId, int cost) {

        UserBalance userBalance = userBalanceRepository.findByUserId(userId)
                .orElseThrow(UserBalanceNotFoundException::new);

        return userBalance.getBalance() >= cost;
    }
}
