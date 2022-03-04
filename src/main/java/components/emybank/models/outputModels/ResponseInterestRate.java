package components.emybank.models.outputModels;
import components.emybank.models.inputModels.InterestRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseInterestRate extends ResponseBase {
    public List<InterestRate> Data;
}
