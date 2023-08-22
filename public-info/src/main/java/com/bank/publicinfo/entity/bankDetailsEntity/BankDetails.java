package com.bank.publicinfo.entity.bankDetailsEntity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_details", schema = "public_bank_information")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, name = "bik")
    Long bik;
    @Column(unique = true, name = "inn")
    Long inn;
    @Column(unique = true, name = "kpp")
    Long kpp;
    @Column(unique = true, name = "cor_account")
    Integer corAccount;
    @Column(name = "city")
    String city;
    @Column(name = "joint_stock_company")
    String jointStockCompany;
    @Column(name = "name")
    String name;

    @JsonManagedReference
    @OneToMany(targetEntity = License.class, mappedBy = "bankDetailsId")//orphanRemoval = true
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<License> licenseList;

    @JsonManagedReference
    @OneToMany(targetEntity = Certificate.class, mappedBy = "bankDetailsId")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Certificate> certificateList;
}