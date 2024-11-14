package sdu.colloborIQ.colloborIQ.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.repository.QuestionRepository;

import java.util.Date;
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
   @Transactional
    public void save(Question question){
        question.setTime(new Date());
        questionRepository.save(question);
    }


    public List<Question> searchWithKeyWord(String keyword) {
        return questionRepository.findByQuestionContainingIgnoreCase(keyword);
    }
}
