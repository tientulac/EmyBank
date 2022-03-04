package components.emybank.models.outputModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo {
    private int user_id;
    private String userName;
    private int accountType_id;
    private String accountType_name;
    private double totalAmount;
    private String fullName;
    private String address;
    private String phone;
    private String gender_name;
    private LocalDate birth;
    private String image;
}
