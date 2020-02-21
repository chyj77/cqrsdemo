package com.example.cqrs.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "reward")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    private Integer id;

    /**
     * order_id
     */
    @Column
    private String orderId;
    @Column
    private String orderitemId;
    @Column
    private Integer userId;
    @Column
    private Integer buyuserId;
    @Column
    private Integer level;
    @Column
    private Integer buyuserlevel;

    /**
     * product_id
     */
    @Column
    private String productId;

    /**
     * product_name
     */
    @Column
    private String productName;

    /**
     * stock
     */
    @Column
    private Integer stock;

    /**
     * price
     */
    @Column
    private BigDecimal price;

    /**
     * vip1_commission
     */
    @Column
    private BigDecimal vip1Commission;

    /**
     * vip2_commission
     */
    @Column
    private BigDecimal vip2Commission;
    @Column
    private BigDecimal commission;

    public Reward(OrderItem orderItem, Integer buyuserId, Integer buyuserlevel, User parent) {
        this.vip1Commission = orderItem.getVip1Commission();
        this.vip2Commission = orderItem.getVip2Commission();
        this.orderId = orderItem.getOrder().getOrderId();
        this.buyuserId = buyuserId;
        this.buyuserlevel = buyuserlevel;
        this.userId = parent.getId();
        this.level = parent.getLevel();
        this.price = orderItem.getPrice();
        this.productId = orderItem.getProductId();
        this.stock = orderItem.getStock();
        this.productName = orderItem.getProductName();
        this.orderitemId = orderItem.getOrderitemId();
    }
}
