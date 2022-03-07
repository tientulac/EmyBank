package components.emybank.models.dtos;

import components.emybank.models.inputModels.Loan;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class LoanDTO {
    private int id;
    private String object;
    private double limited;
    private int rate_id;
    private double rate_name;
    private String purpose;
    private String formality;
    private double amount;
    private int account_id;
    private Date createdAt;
    private Date updateAt;
}
