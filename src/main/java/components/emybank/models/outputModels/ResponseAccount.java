package components.emybank.models.outputModels;

import components.emybank.models.inputModels.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseAccount extends ResponseBase {
    Account Data;
}
