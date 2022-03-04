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
@Table(name = "interestRate")
@Entity
public class InterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "rate")
    private int rate;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updateAt")
    @UpdateTimestamp
    private Date updateAt;
}
