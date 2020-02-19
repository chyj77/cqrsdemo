package com.example.cqrs.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name="user_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    @Column
    private Integer id;
    @Column
    private Integer userId;

    /**
     * user_name
     */

    @Column
    private String userName;

    /**
     * level
     */

    @Column
    private Integer level;

    /**
     * parent_id
     */

    @Column
    private Integer parentId;

    @Column
    private BigDecimal balance;

}
