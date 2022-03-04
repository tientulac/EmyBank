package components.emybank.controllers;

import components.emybank.models.inputModels.InterestRate;
import components.emybank.models.inputModels.Loan;
import components.emybank.models.outputModels.ResponseBase;
import components.emybank.models.outputModels.ResponseInterestRate;
import components.emybank.models.outputModels.ResponseLoan;
import components.emybank.models.outputModels.StatusID;
import components.emybank.services.interestRate.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/interestRate") // router
public class InterestRateController {
    @Autowired
    InterestRateService interestRateService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseInterestRate getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        ResponseInterestRate res = new ResponseInterestRate();
        try {
            List<InterestRate> listInterestRate = interestRateService.findAll();
            res.Status = StatusID.Success.ordinal();
            res.Message = "Success !";
            res.Limit = limit;
            res.Page = page;
            res.Data = listInterestRate;
        }
        catch (Exception ex) {
            res.Status = StatusID.InternalServer.ordinal();
            res.Message = ex.getMessage();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseBase insert(@RequestBody InterestRate interestRate) {
        ResponseBase res = new ResponseBase();
        try {
            if (interestRate != null) {
                interestRate.setCreatedAt(new Date());
                interestRateService.insertOne(interestRate);
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
    public ResponseBase update(@RequestBody InterestRate updateInterestRate) {
        ResponseBase res = new ResponseBase();
        try {
            InterestRate interestRate = interestRateService.findById(updateInterestRate.getId());
            if (interestRate != null) {
                updateInterestRate.setCreatedAt(interestRate.getCreatedAt());
                updateInterestRate.setUpdateAt(new Date());
                interestRateService.updateOne(updateInterestRate);
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
            InterestRate interestRate = interestRateService.findById(id);
            if (interestRate != null) {
                interestRateService.deleteOne(id);
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
}
