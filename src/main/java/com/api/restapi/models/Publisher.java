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
@Table(name = "publishers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Publisher extends BaseEntity {

    @Column(name = "name")
    @NotNull
    String name;

    @Column(name = "address")
    @NotNull
    String address;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    //@JsonManagedReference("publisher")
    @JsonIgnore
    List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        this.bookList.add(book);
    }
}
