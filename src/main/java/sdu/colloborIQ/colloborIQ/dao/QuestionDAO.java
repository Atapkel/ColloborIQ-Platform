package sdu.colloborIQ.colloborIQ.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sdu.colloborIQ.colloborIQ.model.Question;

import java.util.ArrayList;
import java.util.List;
@Component
public class QuestionDAO {
    private final JdbcTemplate jdbcTemplate;

    public QuestionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> index(){
        return jdbcTemplate.query("SELECT * FROM Questions",
                new BeanPropertyRowMapper<>(Question.class));
    }
    public Question show(int id){
        return jdbcTemplate.query("SELECT * FROM Questions WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Question.class))
                .stream().findAny().orElse(null);
    }

    public void save(Question question) {
        jdbcTemplate.update("INSERT INTO Questions(question) values(?)",
                new Object[]{question.getQuestion()});
    }
}
