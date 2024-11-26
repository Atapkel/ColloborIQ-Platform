package sdu.colloborIQ.colloborIQ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdu.colloborIQ.colloborIQ.model.Sdudent;

import java.util.Optional;

@Repository
public interface SdudentRepository extends JpaRepository<Sdudent, Integer> {
    Optional<Sdudent> findByUsername(String username);
}
