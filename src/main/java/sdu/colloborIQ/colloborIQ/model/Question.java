package sdu.colloborIQ.colloborIQ.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "question")
    private List<Comment> comments;
    public Question(){
    }
    public void addComment(Comment comment){
        if (comments == null)comments = new ArrayList<>();
        comments.add(comment);
        comment.setQuestion(this);
    }
    public List<Comment> getComments() {
        return comments;
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

    public void setQuestion(String question) {
        this.question = question;
    }
}
