package com.example.cqrs.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  product
 * @author 大狼狗 2020-02-18
 */
@Entity(name="product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */

    @Column
    private Integer id;

    /**
     * name
     */

    @Column
    private String name;

    /**
     * price
     */

    @Column
    private BigDecimal price;

    /**
     * vip1_commission
     */

    @Column(name = "vip1_commission")
    private BigDecimal vip1Commission;

    /**
     * vip2_commission
     */

    @Column(name = "vip2_commission")
    private BigDecimal vip2Commission;

    /**
     * stock
     */
    @Column
    private Integer stock;
}