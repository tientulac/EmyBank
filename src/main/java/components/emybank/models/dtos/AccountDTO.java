package components.emybank.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String userName;
    private String password;
    private String confirmPassword;
    private int accountType_id;
    private double totalAmount;
    private String fullName;
    private String address;
    private String phone;
    private int gender;
    private LocalDate birth;
    private String image;
}
