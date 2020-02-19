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
@EqualsAndHashCode(of = { "id" })
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
    private Integer orderId;
    @Column
    private Integer userId;
    @Column
    private Integer level;

    /**
     * product_id
     */
    @Column
    private Integer productId;

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
}
