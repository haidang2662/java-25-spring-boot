package com.example.adminweb.model.response;

import com.example.adminweb.entity.Telephone;
import com.example.adminweb.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelephoneBuyerResponse {


    private int id;

    private Buyer buyer;

    private Telephone telephone;

    private int quantity;

    private LocalDate createdDate;


}
