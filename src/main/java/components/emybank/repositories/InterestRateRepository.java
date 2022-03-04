package components.emybank.repositories;

import components.emybank.models.inputModels.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRateRepository extends JpaRepository<InterestRate, Integer> {
}
