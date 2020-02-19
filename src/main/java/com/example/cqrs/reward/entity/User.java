package com.example.cqrs.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    @Column
    private Integer id;

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
}
