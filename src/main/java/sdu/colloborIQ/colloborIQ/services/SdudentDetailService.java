package sdu.colloborIQ.colloborIQ.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sdu.colloborIQ.colloborIQ.model.Sdudent;
import sdu.colloborIQ.colloborIQ.repository.SdudentRepository;

import java.util.Optional;

@Service
public class SdudentDetailService implements UserDetailsService {
    private final SdudentRepository sdudentRepository;

    public SdudentDetailService(SdudentRepository sdudentRepository) {
        this.sdudentRepository = sdudentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Sdudent> sdudent = sdudentRepository.findByUsername(username);
        if (sdudent.isPresent()){
            var obj = sdudent.get();
            return User.builder()
                    .username(obj.getUsername())
                    .password(obj.getPassword())
                    .build();
        }else throw new UsernameNotFoundException("Қолданушы аты дұрыс емес!");
    }
}
