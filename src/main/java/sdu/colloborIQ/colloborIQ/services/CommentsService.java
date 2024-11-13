package sdu.colloborIQ.colloborIQ.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.repository.CommentsRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final QuestionsService questionsService;

    public CommentsService(CommentsRepository commentsRepository, QuestionsService questionsService) {
        this.commentsRepository = commentsRepository;
        this.questionsService = questionsService;
    }

    public Optional<Comment> findById(int id) {
        return commentsRepository.findById(id);
    }

    @Transactional
    public void saveByQuestionId(int questionId, Comment comment) {
        Question questionToAddComment = questionsService.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        questionToAddComment.addComment(comment);

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
