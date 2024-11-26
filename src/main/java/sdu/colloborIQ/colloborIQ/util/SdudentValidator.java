package sdu.colloborIQ.colloborIQ.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sdu.colloborIQ.colloborIQ.dto.SdudentDTO;
import sdu.colloborIQ.colloborIQ.services.SdudentService;

@Component
public class SdudentValidator implements Validator {
    private final SdudentService sdudentService;

    public SdudentValidator(SdudentService sdudentService) {
        this.sdudentService = sdudentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SdudentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SdudentDTO sdudentDTO = (SdudentDTO) target;
        if (sdudentService.findByUsername(sdudentDTO.getUsername()).isPresent()){
            errors.rejectValue("username","","Қолданушы аты бос емес!");
        }
        if (sdudentDTO.getAge() < 0){
            errors.rejectValue("age", "", "Жасыңыз 0 ден жоғар болуы керек!");
        }
        if (sdudentDTO.getPassword().length() < 5){
            errors.rejectValue("password", "", "Құпия сөз ұзындығы 5 тен көп болу керек");
        }
    }
}
