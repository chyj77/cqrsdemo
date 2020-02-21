package com.example.cqrs.reward.handler.account;

import com.example.cqrs.reward.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author albert
 */
public interface AccountRepository extends JpaRepository<UserAccount, Integer> {

    @Query(value = "from user_account where userId = ?1 ")
    UserAccount findAccountByUserId(Integer userId);
}
