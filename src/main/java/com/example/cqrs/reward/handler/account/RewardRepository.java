package com.example.cqrs.reward.handler.account;

import com.example.cqrs.reward.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author albert
 */
public interface RewardRepository extends JpaRepository<Reward, Integer> {
}
