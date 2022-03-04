package components.emybank.controllers;

import components.emybank.models.inputModels.Account;
import components.emybank.models.inputModels.Transaction;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.ResponseTransaction;
import components.emybank.models.outputModels.StatusID;
import components.emybank.services.account.AccountService;
import components.emybank.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/transaction") // router
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseTransaction getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        ResponseTransaction res = new ResponseTransaction();
        try {
            List<Transaction> lisTransaction = transactionService.findAll();
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.Data = lisTransaction;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{account_id}")
    public ResponseTransaction getListByAccountId(
            @RequestParam int account_id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        ResponseTransaction res = new ResponseTransaction();
        try {
            List<Transaction> lisTransaction = transactionService.findByAccountId(account_id);
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.Data = lisTransaction;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseBase deposit(@RequestBody Transaction transaction) {
        ResponseBase res = new ResponseBase();
        try {
            if (transaction != null) {
                Account from_account = accountService.findById(transaction.getFrom_account());
                Account to_account = accountService.findById(transaction.getTo_account());
                double extraMoney = from_account.getTotalAmount() - transaction.getAmount();
                if (extraMoney >= 0) {
                    double lostMoney = to_account.getTotalAmount() + transaction.getAmount();
                    from_account.setTotalAmount(extraMoney);
                    to_account.setTotalAmount(lostMoney);
                    transaction.setCreatedAt(new Date());
                    transactionService.deposit(transaction);
                    accountService.updateOne(from_account);
                    accountService.updateOne(to_account);
                    res.Status = StatusID.Success.ordinal();
                    res.Message = "Success !";
                }
                else {
                    res.Status = StatusID.InternalServer.ordinal();
                    res.Message = "Total amount in your account is not enough to conduct transaction";
                }
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
}
