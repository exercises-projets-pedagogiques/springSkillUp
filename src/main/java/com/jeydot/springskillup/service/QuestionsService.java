package com.jeydot.springskillup.service;

import com.jeydot.springskillup.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class QuestionsService {
    private final HashMap<Integer, Question>    questions = new HashMap<Integer, Question>();
    int                                         id = 0;

    public QuestionsService() {}

    public List<Question> loadQuizzes(){
        return new ArrayList<>(questions.values());
    }

    public void addQuiz(Question question){
        if (!questions.containsValue(question)){
            question.setId(++id);
            questions.put(id, question);
        }
        else{
            System.out.println("Question already exists");
        }
    }

    public void editQuiz(Question question){
        if (questions.containsKey(question.getId())){
            questions.put(question.getId(), question);
        }
    }

    public void deleteQuiz(int id){
        questions.remove(id);
    }
}
