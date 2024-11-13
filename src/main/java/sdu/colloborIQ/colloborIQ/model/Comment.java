package sdu.colloborIQ.colloborIQ.model;

import jakarta.persistence.*;

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
