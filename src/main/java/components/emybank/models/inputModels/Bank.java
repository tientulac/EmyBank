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
@Table(name = "bank")
@Entity

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "nativeName")
    private String nativeName;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "facility")
    private String facility;

    @Column(name = "member")
    private int member;

    @Column(name = "phone")
    private String phone;

    @Column(name = "director")
    private String director;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updateAt")
    @UpdateTimestamp
    private Date updateAt;
}
