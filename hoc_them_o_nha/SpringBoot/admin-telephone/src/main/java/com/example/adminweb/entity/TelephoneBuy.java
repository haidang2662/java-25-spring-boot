package com.example.adminweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "telephoneBuy")
public class TelephoneBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Mỗi người thuê có thể thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một người
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer; // người thuê

    @ManyToOne // Mỗi cuốn sách có thể được thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một cuốn sách
    @JoinColumn(name = "telephone_id", referencedColumnName = "id")
    private Telephone telephone;

    @Column(nullable = false)
    private int quantity;

    private LocalDate createdDate; // ngày mua

}
