package components.emybank.controllers;

import components.emybank.models.inputModels.Bank;
import components.emybank.models.outputModels.ResponseBank;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.StatusID;
import components.emybank.services.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/bank") // router
public class BankController {
    @Autowired
    BankService bankService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseBank getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        ResponseBank res = new ResponseBank();
        try {
            List<Bank> listBank = bankService.findAll();
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.listBank = listBank;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseBase update(@RequestBody Bank updateBank) {
        ResponseBase res = new ResponseBase();
        try {
            Bank bank = bankService.findById(updateBank.getId());
            if (bank != null) {
                updateBank.setUpdateAt(new Date());
                bankService.updateOne(updateBank);
                res.Status = StatusID.Success.ordinal();
                res.Message = "Success !";
            }
            else {
                res.Status = StatusID.InternalServer.ordinal();
                res.Message = "This bank doesnt have this id !";
            }
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return  res;
    }
}
