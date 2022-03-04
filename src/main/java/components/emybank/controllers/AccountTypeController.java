package components.emybank.controllers;

import components.emybank.models.inputModels.AccountType;
import components.emybank.models.outputModels.ResponseAccountType;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.StatusID;
import components.emybank.services.accountType.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/accountType") // router
public class AccountTypeController {
    @Autowired
    AccountTypeService accountTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseAccountType getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        ResponseAccountType res = new ResponseAccountType();
        try {
            List<AccountType> listAccountType = accountTypeService.findAll();
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.Data = listAccountType;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseBase insert(@RequestBody AccountType accountType) {
        ResponseBase res = new ResponseBase();
        try {
            if (accountType != null) {
                accountType.setCreatedAt(new Date());
                accountTypeService.insertOne(accountType);
                res.Status = StatusID.Success.ordinal();
                res.Message = "Success !";
            }
            else {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "Bad request";
            }
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseBase update(@RequestBody AccountType updateAccountType) {
        ResponseBase res = new ResponseBase();
        try {
            AccountType accountType = accountTypeService.findById(updateAccountType.getId());
            if (accountType != null) {
                updateAccountType.setCreatedAt(accountType.getCreatedAt());
                updateAccountType.setUpdateAt(new Date());
                accountTypeService.updateOne(updateAccountType);
                res.Status = StatusID.Success.ordinal();
                res.Message = "Success !";
            }
            else {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "This type doesnt have this id !";
            }
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseBase delete(@PathVariable int id) {
        ResponseBase res = new ResponseBase();
        try {
            boolean rs = accountTypeService.deleteOne(id);
            if (!rs) {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "Deleted fail !";
            }
            else {
                res.Status = StatusID.Success.ordinal();
                res.Message = "Success !";
            }
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }
}
