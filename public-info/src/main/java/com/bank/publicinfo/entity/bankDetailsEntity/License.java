package com.bank.publicinfo.entity.bankDetailsEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "license", schema = "public_bank_information")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class License {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    @Type(type = "org.hibernate.type.ImageType")
    byte[] photo;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JsonBackReference
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id")
    BankDetails bankDetailsId;
}