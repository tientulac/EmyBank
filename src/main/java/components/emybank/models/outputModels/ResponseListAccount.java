package components.emybank.models.outputModels;

import components.emybank.models.inputModels.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseListAccount extends ResponseBase{
    public List<Account> Data;
}
