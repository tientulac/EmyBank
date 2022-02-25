package components.emybank.repositories;

import components.emybank.models.inputModels.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
