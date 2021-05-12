package com.example.androidinternshippart3.readjson;

public class ObjectJSON {

    private String numberQuestion;
    private String question;

    public ObjectJSON(String numberQuestion, String question) {
        this.numberQuestion = numberQuestion;
        this.question = question;
    }

    public String getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(String numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}