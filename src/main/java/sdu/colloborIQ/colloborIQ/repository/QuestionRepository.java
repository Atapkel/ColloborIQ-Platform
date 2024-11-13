package sdu.colloborIQ.colloborIQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sdu.colloborIQ.colloborIQ.model.Question;

@Repository
@Transactional(readOnly = true)
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
