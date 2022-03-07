package components.emybank.repositories;

import components.emybank.models.inputModels.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Query("SELECT l FROM Loan l WHERE l.account_id = ?1")
    List<Loan> findLoanByAccountId(int id);
}
