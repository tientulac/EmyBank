package components.emybank.controllers;

import components.emybank.models.dtos.AccountDTO;
import components.emybank.models.inputModels.Account;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.ResponseLogin;
import components.emybank.models.outputModels.StatusID;
import components.emybank.models.outputModels.UserInfo;
import components.emybank.services.account.AccountService;
import components.emybank.services.accountType.AccountTypeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountTypeService accountTypeService;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "EmyBankSecurity";
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public String generateToken(UserInfo userInfo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserInfo", userInfo);
        return doGenerateToken(claims, userInfo.getUserName());
    }


    public String getAccountTypeName(int accountType_id) {
        if (accountType_id > 0) {
            return accountTypeService.findById(accountType_id).getName();
        }
        else {
            return "";
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public ResponseBase register(@RequestBody AccountDTO accountDTO) {
        ResponseBase res = new ResponseBase();
        try {
            System.out.println(accountDTO.getPassword());
            System.out.println(accountDTO.getConfirmPassword());
            if (accountDTO.getPassword().equals(accountDTO.getConfirmPassword())) {
                accountService.register(accountDTO);
                res.Status = StatusID.Success.ordinal();
                res.Message = "Success !";
            }
            else {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "You should input password like confirm password !";
            }
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }

    @RequestMapping(method = RequestMethod.POST, path = "login")
    public ResponseLogin login(@RequestBody Account account) {
        ResponseLogin res = new ResponseLogin();
        try {
            Account _account = accountService.login(account);
            if (_account != null) {
                res.Status = StatusID.Success.ordinal();
                res.Message = "Login success !";
                UserInfo Info = new UserInfo();
                Info.setUser_id(_account.getId());
                Info.setUserName(_account.getUserName());
                Info.setAccountType_id(_account.getAccountType_id());
                Info.setTotalAmount(_account.getTotalAmount());
                Info.setFullName(_account.getFullName());
                Info.setAddress(_account.getAddress());
                Info.setPhone(_account.getPhone());
                Info.setGender_name((_account.getGender() == 0) ? "Female": "Male");
                Info.setBirth(_account.getBirth());
                Info.setImage(_account.getImage());
                Info.setAccountType_name(getAccountTypeName(_account.getAccountType_id()));
                res.setUserInfo(Info);
                res.setToken(generateToken(Info));
            }
            else {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "Information login is not true !";
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }
}
