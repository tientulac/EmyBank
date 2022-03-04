package components.emybank.models.outputModels;

import components.emybank.models.inputModels.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseTransaction extends ResponseBase {
    public List<Transaction> Data;
}
