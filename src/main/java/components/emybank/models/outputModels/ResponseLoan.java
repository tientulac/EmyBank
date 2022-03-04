package components.emybank.models.outputModels;

import components.emybank.models.inputModels.AccountType;
import components.emybank.models.inputModels.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseLoan extends ResponseBase {
    public List<Loan> Data;
}
