package components.emybank.services.interestRate;

import components.emybank.models.inputModels.InterestRate;
import components.emybank.models.inputModels.Loan;
import components.emybank.repositories.InterestRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class InterestRateServiceImpl implements  InterestRateService{

    @Autowired
    InterestRateRepository interestRateRepository;

    @Override
    public List<InterestRate> findAll() {
        return interestRateRepository.findAll();
    }

    @Override
    public InterestRate updateOne(InterestRate interestRate) {
        return interestRateRepository.save(interestRate);
    }

    @Override
    public InterestRate insertOne(InterestRate interestRate) {
        return interestRateRepository.save(interestRate);
    }

    @Override
    public InterestRate findById(int id) {
        return interestRateRepository.findById(id).get();
    }

    @Override
    public boolean deleteOne(int id) {
        InterestRate interestRate = interestRateRepository.findById(id).get();
        if (interestRate != null) {
            interestRateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
