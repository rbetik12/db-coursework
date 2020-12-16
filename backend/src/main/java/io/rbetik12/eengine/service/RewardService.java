package io.rbetik12.eengine.service;

import io.rbetik12.eengine.entity.Reward;
import io.rbetik12.eengine.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    @Autowired
    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> getAll() {
        return rewardRepository.findAll();
    }
}
