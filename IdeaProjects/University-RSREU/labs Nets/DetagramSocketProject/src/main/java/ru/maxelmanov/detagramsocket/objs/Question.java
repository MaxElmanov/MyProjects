package ru.maxelmanov.detagramsocket.objs;

import java.util.List;

public class Question
{
    private String testQuestion;
    private List<String> answers;
    private String rightAnswer;

    public Question(String testQuestion, List<String> answers, String rightAnswer)
    {
        this.testQuestion = testQuestion;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public String getTestQuestion()
    {
        return testQuestion;
    }

    public List<String> getAnswers()
    {
        return answers;
    }

    public String getRightAnswer()
    {
        return rightAnswer;
    }

    public void analizeResult(){

    }
}
