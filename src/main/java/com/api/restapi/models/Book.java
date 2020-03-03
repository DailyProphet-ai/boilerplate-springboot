package com.api.restapi.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

    @Column(name = "name")
    @NotNull
    String name;

    @NotNull
    @Column(name = "price")
    double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", nullable = false)
    //@JsonBackReference("author")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id", nullable = false)
    //@JsonBackReference("publisher")
    private Publisher publisher;

}
