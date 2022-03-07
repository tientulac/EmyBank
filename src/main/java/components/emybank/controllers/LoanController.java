package components.emybank.controllers;

import components.emybank.ExcelExport.LoanExcelExporter;
import components.emybank.ExcelExport.TransactionExcelExporter;
import components.emybank.models.inputModels.Account;
import components.emybank.models.inputModels.InterestRate;
import components.emybank.models.inputModels.Loan;
import components.emybank.models.inputModels.Transaction;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.ResponseLoan;
import components.emybank.models.outputModels.StatusID;
import components.emybank.services.account.AccountService;
import components.emybank.services.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/loan") // router
public class LoanController {
    @Autowired
    LoanService loanService;

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseLoan getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        ResponseLoan res = new ResponseLoan();
        try {
            List<Loan> listLoan = loanService.findAll();
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.Data = listLoan;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseBase insert(@RequestBody Loan loan) {
        ResponseBase res = new ResponseBase();
        try {
            if (loan != null) {
                loan.setCreatedAt(new Date());
                loanService.insertOne(loan);
                Account account = accountService.findById(loan.getAccount_id());
                double newTotal = account.getTotalAmount() + loan.getAmount();
                account.setTotalAmount(newTotal);
                accountService.updateOne(account);
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
    public ResponseBase update(@RequestBody Loan updateLoan) {
        ResponseBase res = new ResponseBase();
        try {
            Loan loan = loanService.findById(updateLoan.getId());
            if (loan != null) {
                updateLoan.setUpdateAt(new Date());
                loanService.updateOne(updateLoan);
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

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseBase delete(@RequestParam int id) {
        ResponseBase res = new ResponseBase();
        try {
            Loan loan = loanService.findById(id);
            if (loan != null) {
                loanService.deleteOne(id);
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

    @RequestMapping(method = RequestMethod.GET, path = "/account")
    public ResponseLoan getByAccountId(@RequestParam int account_id) {
        ResponseLoan res = new ResponseLoan();
        try {
            List<Loan> listLoan = loanService.findByAccountId(account_id);
            if (listLoan != null) {
                res.Data = listLoan;
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

    @RequestMapping(method = RequestMethod.GET, path = "/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=loan.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Loan> listLoans = loanService.findAll();

        LoanExcelExporter excelExporter = new LoanExcelExporter(listLoans);

        excelExporter.export(response);
    }
}
