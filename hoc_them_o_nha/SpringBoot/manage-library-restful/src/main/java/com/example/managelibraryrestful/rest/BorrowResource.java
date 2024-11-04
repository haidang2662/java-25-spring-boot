package com.example.managelibraryrestful.rest;

import com.example.managelibraryrestful.exceptionhandling.exception.InvalidBookBorrowQuantityException;
import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.BorrowRequest;
import com.example.managelibraryrestful.model.response.BorrowResponse;
import com.example.managelibraryrestful.service.BorrowService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/borrows")
public class BorrowResource {

    BorrowService borrowService;

    @GetMapping
    public List<BorrowResponse> getBorrows() {
        return borrowService.getBorrows();
    }

    @PostMapping
    public BorrowResponse createBorrow(@RequestBody @Valid BorrowRequest request)
            throws ObjectNotFoundException, InvalidBookBorrowQuantityException {
        return borrowService.creatBorrow(request);
    }

    @GetMapping("/{id}")
    public BorrowResponse getBorrowDetail(@PathVariable("id") Long idBorrow) throws ObjectNotFoundException {
        return borrowService.getBorrowDetail(idBorrow);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable("id") Long idBorrow){
        borrowService.deleteBorrow(idBorrow);
    }

    @PutMapping("/{id}")
    public BorrowResponse updateBorrow(@RequestBody BorrowRequest request , @PathVariable("id") Long idBorrow) throws ObjectNotFoundException {
        return borrowService.updateBorrow(request,idBorrow);
    }

}
