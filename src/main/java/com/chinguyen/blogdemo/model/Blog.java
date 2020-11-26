package com.chinguyen.blogdemo.model;

import javafx.scene.text.Text;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table()
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "longtext")
    private String text;
    private String imageURL;
    @Transient
    private MultipartFile image;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn
    private Category category;

    public Blog() {
    }

    public Blog(Long id, String title, String text, String imageURL, MultipartFile image, java.util.Date date, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageURL = imageURL;
        this.image = image;
        this.date = date;
        this.category = category;
    }

    public Blog(Long id, String title, String text, String imageURL, MultipartFile image, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageURL = imageURL;
        this.image = image;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
