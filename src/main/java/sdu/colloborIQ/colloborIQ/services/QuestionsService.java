package sdu.colloborIQ.colloborIQ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {
    private final QuestionRepository questionRepository;

    public QuestionsService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll(){
        return  questionRepository.findAll().reversed();
    }
    public Optional<Question> findById(int id){
        return questionRepository.findById(id);
    }
    public void save(Question question){
        System.out.println("I am save method");
        questionRepository.save(question);
    }


}
