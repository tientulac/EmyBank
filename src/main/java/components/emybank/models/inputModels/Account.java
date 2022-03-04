package components.emybank.models.inputModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "accountType_id")
    private int accountType_id;

    @Column(name = "totalAmount")
    private double totalAmount;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private int gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "image")
    private String image;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updateAt")
    @UpdateTimestamp
    private Date updateAt;
}

