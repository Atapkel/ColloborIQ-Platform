package sdu.colloborIQ.colloborIQ.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.repository.CommentsRepository;
import sdu.colloborIQ.colloborIQ.repository.QuestionRepository;

import java.util.*;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final QuestionRepository questionRepository;

    public CommentsService(CommentsRepository commentsRepository, QuestionRepository questionRepository) {
        this.commentsRepository = commentsRepository;
        this.questionRepository = questionRepository;
    }

    public Optional<Comment> findById(int id) {
        return commentsRepository.findById(id);
    }


    @Transactional
    public void saveByQuestionId(int questionId, Comment comment) {
        comment.setTime(new Date());
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.addComment(comment);
        commentsRepository.save(comment);
    }


    public void upVote(int id) {
        commentsRepository.incrementUpVotes(id);
    }

    public void downVote(int id) {
        commentsRepository.incrementDownVotes(id);
    }

    public List<Comment> getCommentsByQuestionId(int id) {

        List<Comment> comments = commentsRepository.findCommentsByQuestionId(id);
        Collections.sort(comments);

        return comments;
    }
}
