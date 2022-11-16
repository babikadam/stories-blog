package com.blog.storiesblog.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "fav_id")
    private Long fav_id;

    @NotNull
    @Column(name = "favName", unique = true, nullable = false)
    private String favName;

    public Favourite() { }


}
