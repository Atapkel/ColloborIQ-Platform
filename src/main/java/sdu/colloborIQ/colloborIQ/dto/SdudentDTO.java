package sdu.colloborIQ.colloborIQ.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SdudentDTO {
    @NotEmpty(message = "Қолданушы аты сөз бос болмауы керек")
    private String username;
    @NotEmpty(message = "Есіміңіз сөз бос болмауы керек")
    private String name;
    @NotEmpty(message = "Тегіңіз сөз бос болмауы керек")
    private String surname;
    @NotEmpty(message = "Құпия сөз бос болмауы керек")
    private String password;
    @Min(value = 0, message = "Жас 0 ден үлкен болуы керек")
    private Integer age;

    @Override
    public String toString() {
        return "SdudentDTO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
