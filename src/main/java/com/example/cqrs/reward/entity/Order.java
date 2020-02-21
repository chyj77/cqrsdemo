package com.example.cqrs.reward.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/**
 * order
 *
 * @author  2020-02-18
 */
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Data
@ToString
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

    @Column
    private String OrderId;

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

    @OneToMany(targetEntity=OrderItem.class,mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItemSet =new ArrayList<>();
}
