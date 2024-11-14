package sdu.colloborIQ.colloborIQ.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.dto.CommentDTO;
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
    public void saveByQuestionId(int questionId, CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setDownVote(0);
        comment.setDownVote(0);
        comment.setTime(new Date());
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.addComment(comment);
        questionRepository.save(question);
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
