package components.emybank.models.inputModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "loan")
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "object")
    private String object;

    @Column(name = "limited")
    private double limited;

    @Column(name = "rate_id")
    private int rate_id;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "formality")
    private String formality;

    @Column(name = "amount")
    private double amount;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updateAt")
    @UpdateTimestamp
    private Date updateAt;
}
