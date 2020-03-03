package com.api.restapi.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import com.api.restapi.constants.ApiConstants;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class BaseEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",
            strategy = "uuid2")
    @Column(name = "id", columnDefinition = "uuid",
            updatable = false, nullable = false)
    UUID id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JsonProperty("created_at")
    @JsonFormat(pattern = ApiConstants.DATE_FORMAT)
    @DateTimeFormat(pattern = ApiConstants.DATE_FORMAT)
    Date createdAt;


    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    @JsonFormat(pattern = ApiConstants.DATE_FORMAT)
    @DateTimeFormat(pattern = ApiConstants.DATE_FORMAT)
    Date updatedAt;


    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    @JsonProperty("deleted_at")
    @JsonFormat(pattern = ApiConstants.DATE_FORMAT)
    @DateTimeFormat(pattern = ApiConstants.DATE_FORMAT)
    Date deletedAt;
}