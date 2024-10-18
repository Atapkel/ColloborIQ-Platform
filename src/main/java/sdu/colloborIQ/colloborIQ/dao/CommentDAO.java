package sdu.colloborIQ.colloborIQ.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sdu.colloborIQ.colloborIQ.model.Comment;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentDAO {
    private final JdbcTemplate jdbcTemplate;

    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> getCommentsByQuestionId(int id) {
        return jdbcTemplate.query(
                "select c.comment, c.id from comments as c join questions as q on c.question_id = q.id where c.question_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Comment.class));
    }

    public void saveByQuestionId(int questionId, Comment comment) {
        jdbcTemplate.update("insert into comments(comment, question_id) values(?,?)",
                new Object[]{comment.getComment(), questionId});
    }
}
