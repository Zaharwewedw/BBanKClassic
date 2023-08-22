package com.bank.publicinfo.entity.branchEntity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.OffsetTime;
import java.util.List;

@Entity
@Table(name = "branch", schema = "public_bank_information")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Branch {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "address")
    String address;
    @Column(unique = true, name = "phone_number")
    Long phoneNumber;
    @Column(name = "city")
    String city;
    @Column(name = "start_of_work", columnDefinition = "TIME WITH TIME ZONE")
    OffsetTime startOfWork;
    @Column(name = "end_of_work", columnDefinition = "TIME WITH TIME ZONE")
    OffsetTime endOfWork;
    @JsonManagedReference
    @OneToMany(targetEntity = Atm.class, mappedBy = "branchId")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Atm> atmList;
}
