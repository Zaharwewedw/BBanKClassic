package com.bank.publicinfo.entity.branchEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.OffsetTime;

@Entity
@Table(name = "atm", schema = "public_bank_information")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Atm {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "address")
    String address;
    @Column(name = "start_of_work", columnDefinition = "TIME WITH TIME ZONE")
    @Nullable
    OffsetTime startOfWork;
    @Column(name = "end_of_work", columnDefinition = "TIME WITH TIME ZONE")
    @Nullable
    OffsetTime endOfWork;
    @Column(name = "all_hours")
    Boolean allHours;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JsonBackReference
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    Branch branchId;
}
