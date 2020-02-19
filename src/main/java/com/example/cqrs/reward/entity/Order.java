package com.example.cqrs.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * order
 *
 * @author  2020-02-18
 */
@Entity(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    @Column
    private String name;

    /**
     * user_id
     */
    @Column
    private Integer userId;

    /**
     * price
     */
    @Column
    private BigDecimal price;

    /**
     * create_time
     */
    @Column
    private Date createTime;

    /**
     * 0 创建，1完成
     */
    @Column
    private Integer status;

    @OneToMany(targetEntity=OrderItem.class,mappedBy = "order")
    private Set<OrderItem> orderItemSet =new HashSet<>();
}
