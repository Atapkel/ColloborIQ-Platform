package sdu.colloborIQ.colloborIQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Comment;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByQuestionId(int id);
}
