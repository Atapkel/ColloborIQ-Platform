package sdu.colloborIQ.colloborIQ.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "question")
    private String question;
    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    @Column(name = "author")
    private String author;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date time;

    public Question() {
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void addComment(Comment comment){
        comment.setQuestion(this);
        this.comments.add(comment);
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }


    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return "Сұрақ авторы: " + author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
