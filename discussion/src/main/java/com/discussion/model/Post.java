package com.discussion.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "d_post")
@Entity
@ToString(exclude = {"comments"})
public class Post extends Auditable {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    
    @Lob
    @Column(columnDefinition="TEXT")
    private String content;
    private String description;

    @OneToMany(cascade = {CascadeType.REMOVE},mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments;


}
