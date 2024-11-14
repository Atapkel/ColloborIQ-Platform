package sdu.colloborIQ.colloborIQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Comment;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.upVote = c.upVote + 1 WHERE c.id = :commentId")
    void incrementUpVotes(@Param("commentId") int commentId);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.downVote = c.downVote + 1 WHERE c.id = :commentId")
    void incrementDownVotes(@Param("commentId") int commentId);

    List<Comment> findByQuestionId(int questionId);

    List<Comment> findCommentsByQuestionId(int id);
}
