package com.example.bot;

public class DatabaseTrace {

    private String question;
    private String answer;
    private String category;
    private int id;


    public int getId() {
        return id;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return  answer;
    }
    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    public DatabaseTrace(String question, String answer,String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public DatabaseTrace(int id,String question, String answer,String category) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.category = category;
    }
}