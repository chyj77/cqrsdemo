package com.example.cqrs.reward.handler.account;

import com.example.cqrs.reward.domain.account.events.AccountCreatedEvent;
import com.example.cqrs.reward.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author albert
 */
@Component
@Slf4j
public class AccountEventHandler {

    @Resource
    private  AccountRepository accountRepository;
    @Resource
    private  RewardRepository rewardRepository;
    @Resource
    private  UserRepository userRepository;

    @EventHandler
    void on(AccountCreatedEvent event) {
        log.info("EventHandler AccountCreatedEvent ");
        Order order = event.getOrder();
        List<OrderItem> orderItemSet = order.getOrderItemSet();
        Integer userId = order.getUserId();
        User user = userRepository.getOne(userId);
        Integer buyuserLevel = user.getLevel();
        Integer parentId = user.getParentId();
        if(null != parentId){
            User parent = userRepository.getOne(parentId);
            UserAccount parentAccount = accountRepository.findAccountByUserId(parent.getId());
            User grand = null;
            UserAccount grandAccount = null;
            if(null != parent.getParentId()){
                grand = userRepository.getOne(parent.getParentId());
                grandAccount = accountRepository.findAccountByUserId(grand.getId());
            }
            for(OrderItem orderItem:orderItemSet){
                parentAccount = callReward( orderItem, userId,buyuserLevel, parent,parentAccount,true);
                if(null != grand){
                    grandAccount = callReward( orderItem, userId,buyuserLevel, grand,grandAccount,false);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public UserAccount callReward(OrderItem orderItem,Integer buyuserId, Integer buyuserlevel,User parent,UserAccount account,boolean isParent){
        Integer parentLevel = parent.getLevel();
        Reward reward = new Reward(orderItem,buyuserId,buyuserlevel,parent);
        BigDecimal vip1Commission = reward.getVip1Commission();
        BigDecimal vip2Commission = reward.getVip2Commission();
        Integer stock = reward.getStock();
        BigDecimal commission = BigDecimal.ZERO;
        if(parentLevel==2){
            commission = vip2Commission.multiply(new BigDecimal(stock));
        }
        if(parentLevel==1){
            commission = vip1Commission.multiply(new BigDecimal(stock));
        }
        if(parentLevel==1 && buyuserlevel==3 && isParent){
            commission = vip2Commission.add(vip1Commission).multiply(new BigDecimal(stock));
        }
        reward.setCommission(commission);
        rewardRepository.save(reward);
        if(account==null){
            account = new UserAccount();
            account.setLevel(parentLevel);
            account.setUserId(parent.getId());
            account.setUserName(parent.getUserName());
            account.setBalance(commission);
        }else {
            BigDecimal balance = account.getBalance()==null?BigDecimal.ZERO:account.getBalance();
            account.setBalance(balance.add(commission));
        }
        accountRepository.save(account);
        return account;
    }

/*
    @EventHandler
    void on(TaskTitleModifiedEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setTitle(event.getTitle());
			taskEntryRepository.save(task);
		});
    }

    @EventHandler
    void on(TaskStarredEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setStarred(true);
			taskEntryRepository.save(task);
		});
    }

    @EventHandler
    void on(TaskUnstarredEvent event) {
		taskEntryRepository.findById(event.getId()).ifPresent(task -> {
			task.setStarred(false);
			taskEntryRepository.save(task);
		});
    }
    */

}
