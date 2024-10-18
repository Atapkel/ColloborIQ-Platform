package sdu.colloborIQ.colloborIQ.model;

public class Question {
    private String question;
    private int id;
    public Question(){}
    public Question(String question, int id){
        this.question = question;
        this.id = id;
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
