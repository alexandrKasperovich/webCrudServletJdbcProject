package by.alex.kasperovich.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private int id;

    @Column(name = "operator")
    private String operator;

    @Column(name = "code")
    private int code;

    @Column(name = "phone_number")
    private int phoneNumber;

    @ManyToOne(targetEntity = Person.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person_fk", referencedColumnName = "id_person")
    private Person person;

    public Phone(int id, String operator, int code, int phoneNumber) {
        this.id = id;
        this.operator = operator;
        this.code = code;
        this.phoneNumber = phoneNumber;
    }

    public Phone(String operator, int code, int phoneNumber) {
        this.operator = operator;
        this.code = code;
        this.phoneNumber = phoneNumber;
    }

    public Phone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
