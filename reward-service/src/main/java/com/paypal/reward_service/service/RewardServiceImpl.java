package com.paypal.reward_service.service;

import com.paypal.reward_service.entity.Reward;
import com.paypal.reward_service.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService{
   @Autowired
   private RewardRepository repository;

    @Override
    public Reward senReward(Reward reward) {
        reward.setSentAt(LocalDateTime.now());
                return repository.save(reward);
    }

    @Override
    public List<Reward> getRewardByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
