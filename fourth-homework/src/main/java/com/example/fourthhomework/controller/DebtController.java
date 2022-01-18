package com.example.fourthhomework.controller;

import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.dto.UserDTO;
import com.example.fourthhomework.entity.Debt;
import com.example.fourthhomework.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/debts")
public class DebtController {

    private final DebtService debtService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DebtDTO> saveDebt(@RequestBody DebtDTO debtDTO) {
        var responseDebtDTO = debtService.save(debtDTO);
        return new ResponseEntity<>(responseDebtDTO, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createdDate")
    public ResponseEntity<List<DebtDTO>> findAllByCreatedDateBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        var responseDebtDTOs = debtService.findAllByCreatedDateBetween(startDate, endDate);
        return new ResponseEntity<>(responseDebtDTOs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public ResponseEntity<List<DebtDTO>> findAllByUser(@RequestParam Long userId){
        var responseDebtDTOs = debtService.findAllByUserId(userId);
        return new ResponseEntity<>(responseDebtDTOs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/overdueUser")
    public ResponseEntity<List<DebtDTO>> findAllByExpiryDateAndRealDebtAmount(@RequestParam Long userId){
        var responseDebtDTOs = debtService.findAllByUserIdAndExpiryDateBeforeAndRealDebtAmountIsNotZero(userId);
        return new ResponseEntity<>(responseDebtDTOs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userDebt")
    public ResponseEntity<String> sumInstantDebtByUserId(@RequestParam Long userId){
        var sum = debtService.sumInstantDebtByUserId(userId);
        return new ResponseEntity<>("The user who has " + userId + " id's total debt : " + sum,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "expiryDateUserDebt")
    public ResponseEntity<String> sumRealDebtByUserIdAndExpiryDate(@RequestParam Long userId)
    {
        var sum = debtService.sumRealDebtByUserIdAndExpiryDate(userId);
        return new ResponseEntity<>("The user who has " + userId + " id's total past due debt : " + sum, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "instantLateRaise")
    public ResponseEntity<String> instantLateRaise(@RequestParam Long userId)
    {
        var instantLateRaise = debtService.instantLateRaise(userId);
        return new ResponseEntity<>("The user who has " + userId + " id's instant late raise : " + instantLateRaise, HttpStatus.OK);
    }

}
