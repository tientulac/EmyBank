package components.emybank.models.outputModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseLogin extends ResponseBase{
    private UserInfo UserInfo;
    private String Token;
}
