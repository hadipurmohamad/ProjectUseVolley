package com.example.app4.model;

public class Question {
    private String question;
    private Boolean Answer;

    public Question() {
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAnswer() {
        return Answer;
    }

    public void setAnswer(Boolean answer) {
        Answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", Answer=" + Answer +
                '}';
    }

    public Question(String question, Boolean answer) {
        this.question = question;
        Answer = answer;
    }
}
