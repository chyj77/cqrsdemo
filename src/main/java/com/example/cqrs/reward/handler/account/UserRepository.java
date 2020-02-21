package com.example.cqrs.reward.handler.account;

import com.example.cqrs.reward.entity.User;
import io.undertow.security.idm.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author albert
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
