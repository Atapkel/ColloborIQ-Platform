package sdu.colloborIQ.colloborIQ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.repository.CommentsRepository;

import java.util.List;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }


    public void saveByQuestionId(Question question, Comment comment) {
        question.addComment(comment);
        commentsRepository.save(comment);
    }

    public List<Comment> getCommentsByQuestionId(int id) {
        return commentsRepository.findCommentsByQuestionId(id);
    }
}
