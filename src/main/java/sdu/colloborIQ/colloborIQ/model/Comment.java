package sdu.colloborIQ.colloborIQ.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "comments")

public class Comment implements Comparable<Comment>{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    @Column(name = "upvote")
    private int upVote;
    @Column(name = "downvote")
    private int downVote;
    @Column(name = "author")
    private String author;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date time;
    public Comment(){}

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public Comment(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
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

    public void setId(int id) {
        this.id = id;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Comment o) {
        if (o.upVote > this.upVote)return 1;
        if (o.upVote < this.upVote)return -1;
        return Integer.compare(this.downVote, o.downVote);

    }
}
