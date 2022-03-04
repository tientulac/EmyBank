package components.emybank.services.interestRate;

import components.emybank.models.inputModels.InterestRate;

import java.util.List;

public interface InterestRateService {
    List<InterestRate> findAll();
    InterestRate updateOne(InterestRate interestRate);
    InterestRate insertOne(InterestRate interestRate);
    InterestRate findById(int id);
    boolean deleteOne(int id);
}
