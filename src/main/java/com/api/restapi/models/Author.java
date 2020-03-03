package com.api.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity {

    @Column(name = "name")
    @NotNull
    String name;

    @Column(name = "address")
    @NotNull
    String address;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    //@JsonManagedReference("author")
    @JsonIgnore
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        this.bookList.add(book);
    }
}
