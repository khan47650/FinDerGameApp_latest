package com.example.findergamesapp.models;

import java.io.Serializable;

public class QuizModel implements Serializable {
    String question;
    String answer;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    String questionId;

    public QuizModel() {
    }

    String resultDetails;
    Options options;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }



    public QuizModel(String question,Options options, String answer, String resultDetails) {
        this.question = question;
        this.answer = answer;
        this.options=options;
        this.resultDetails = resultDetails;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
}
