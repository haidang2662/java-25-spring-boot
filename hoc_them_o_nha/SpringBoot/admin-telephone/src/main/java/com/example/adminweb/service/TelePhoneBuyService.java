package com.example.adminweb.service;

import com.example.adminweb.entity.Buyer;
import com.example.adminweb.entity.Telephone;
import com.example.adminweb.entity.TelephoneBuy;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.TelephoneBuyCreationRequest;
import com.example.adminweb.model.request.UpdateTelephoneBuyRequest;
import com.example.adminweb.model.response.TelephoneBuyerResponse;
import com.example.adminweb.repository.TelephoneBuyRepository;
import com.example.adminweb.repository.TelephoneRepository;
import com.example.adminweb.repository.BuyerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelePhoneBuyService {

    ObjectMapper objectMapper;

    BuyerRepository buyerRepository;

    TelephoneRepository telephoneRepository;

    TelephoneBuyRepository telephoneBuyRepository;

    public void create(TelephoneBuyCreationRequest telephoneBuyRequest) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(telephoneBuyRequest.getBuyerId());
        if (buyerOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay nguoi mua");
        }

        Optional<Telephone> telephoneOptional = telephoneRepository.findById(telephoneBuyRequest.getTelephoneId());
        if (telephoneOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay thong tin dien thoai ");
        }

        TelephoneBuy telephoneBuy = TelephoneBuy.builder()
                .buyer(buyerOptional.get())
                .telephone(telephoneOptional.get())
                .quantity(telephoneBuyRequest.getQuantity())
                .createdDate(LocalDate.now())
                .build();

        telephoneBuyRepository.save(telephoneBuy);
    }

    public Page<TelephoneBuyerResponse> findAllTelephoneBuys(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page -1 , pageSize , Sort.by("id").descending());
        Page<TelephoneBuy> telephoneBuyPage = telephoneBuyRepository.findAll(pageable);
        List<TelephoneBuy> telephoneBuys = telephoneBuyPage.getContent();
        List<TelephoneBuyerResponse> telephoneBuyerResponses = telephoneBuys
                .stream()
                .map(telephoneBuyTemp -> objectMapper.convertValue(telephoneBuyTemp , TelephoneBuyerResponse.class))
                .toList();
        return new PageImpl<>(telephoneBuyerResponses, pageable , telephoneBuyPage.getTotalElements());
    }

    public void deleteByIdTelephoneBuy(Long id) {
        telephoneBuyRepository.deleteById(id);
    }


    public UpdateTelephoneBuyRequest findTelephoneBuyById(Long id) {
        Optional<TelephoneBuy> telephoneBuyOptional = telephoneBuyRepository.findById(id);
        if(telephoneBuyOptional.isEmpty()){
            throw new ObjectNotFoundException("Khong tim thay luot mua dien thoai mang id : " +id);
        }

        TelephoneBuy telephoneBuy = telephoneBuyOptional.get();
        UpdateTelephoneBuyRequest request = objectMapper.convertValue(telephoneBuy , UpdateTelephoneBuyRequest.class);
        request.setBuyerId(telephoneBuy.getBuyer().getId());
        request.setTelephoneId(telephoneBuy.getTelephone().getId());
        return request;
    }

    public void updateTelephoneBuy(UpdateTelephoneBuyRequest updateTelephoneBuy) {

        Optional<TelephoneBuy> telephoneBuyOptional = telephoneBuyRepository.findById(updateTelephoneBuy.getId());
        if (telephoneBuyOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy lượt mua dien thoai mang Id: " + updateTelephoneBuy.getId());
        }

        Optional<Buyer> buyerOptional = buyerRepository.findById(updateTelephoneBuy.getBuyerId());
        Optional<Telephone> telephoneOptional = telephoneRepository.findById(updateTelephoneBuy.getTelephoneId());

        TelephoneBuy telephoneBuy = telephoneBuyOptional.get();
        telephoneBuy.setBuyer(objectMapper.convertValue(buyerOptional , Buyer.class));
        telephoneBuy.setTelephone(objectMapper.convertValue(telephoneOptional , Telephone.class));
//        bookBorrow.setCreatedDate(updateBookBorrow.);
        telephoneBuy.setQuantity(updateTelephoneBuy.getQuantity());
        telephoneBuy.setCreatedDate(updateTelephoneBuy.getCreatedDate());

        telephoneBuyRepository.save(telephoneBuy);
    }
}
