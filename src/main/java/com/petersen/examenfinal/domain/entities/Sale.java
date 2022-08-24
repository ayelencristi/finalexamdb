package com.petersen.examenfinal.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sale_id") // Esta columna se crea en SaleDatail
    private List<SaleDetail> saleDetails;

    @Column(nullable = false)
    private BigDecimal commission;


    public void addDetail(SaleDetail saleDetail){
        if(this.saleDetails == null){
            this.saleDetails = new ArrayList<>();
        }
        this.saleDetails.add(saleDetail);
    }
}
