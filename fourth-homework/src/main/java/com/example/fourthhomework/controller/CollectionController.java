package com.example.fourthhomework.controller;

import com.example.fourthhomework.dto.CollectionDTO;
import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.service.CollectionService;
import com.example.fourthhomework.service.impl.CollectionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CollectionDTO> saveCollection(@RequestBody CollectionDTO collectionDTO) {
        var responseCollectionDTO = collectionService.save(collectionDTO);
        return new ResponseEntity<>(responseCollectionDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/collectionDate")
    public ResponseEntity<List<CollectionDTO>> findAllByCollectionDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        var responseCollectionDTOs = collectionService.findAllByCollectionDateBetween(startDate,endDate);
        return new ResponseEntity<>(responseCollectionDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<CollectionDTO>> findAllByUser(@RequestParam Long userId){
        var responseCollectionDTOs = collectionService.findAllByUserId(userId);
        return new ResponseEntity<>(responseCollectionDTOs,HttpStatus.OK);
    }

    @GetMapping(value="/totalLateRaise")
    public ResponseEntity<String> sumLateRaise(@RequestParam Long userId)
    {
        var sum = collectionService.sumMainDebtAmountByUserId(userId);
        return new ResponseEntity<>("The user who has " + userId + " total late raise : " + sum,HttpStatus.OK);
    }

}
