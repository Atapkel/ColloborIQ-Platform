package sdu.colloborIQ.colloborIQ.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.colloborIQ.colloborIQ.dto.SdudentDTO;
import sdu.colloborIQ.colloborIQ.model.Sdudent;
import sdu.colloborIQ.colloborIQ.repository.SdudentRepository;

import java.util.Optional;

@Service
public class SdudentService {
    private final SdudentRepository sdudentRepository;
    private final PasswordEncoder passwordEncoder;

    public SdudentService(SdudentRepository sdudentRepository, PasswordEncoder passwordEncoder) {
        this.sdudentRepository = sdudentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Sdudent> findByUsername(String username){
        return sdudentRepository.findByUsername(username);
    }
    public void save(SdudentDTO sdudentDTO){
        Sdudent sdudent = new Sdudent();
        sdudent.setUsername(sdudentDTO.getUsername());
        sdudent.setName(sdudentDTO.getName());
        sdudent.setSurname(sdudentDTO.getSurname());
        sdudent.setAge(sdudentDTO.getAge());
        sdudent.setPassword(passwordEncoder.encode(sdudentDTO.getPassword()));
        sdudentRepository.save(sdudent);
    }
}
