package components.emybank.models.outputModels;

import components.emybank.models.inputModels.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ResponseAccountType extends ResponseBase {
    public List<AccountType> Data;
}
