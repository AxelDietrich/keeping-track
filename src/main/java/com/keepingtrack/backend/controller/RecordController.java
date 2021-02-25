package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.entities.Subcategory;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.request.RequestAddRecord;
import com.keepingtrack.backend.service.RecordService;
import com.keepingtrack.backend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    SubcategoryService subcategoryService;

    @PostMapping("/{subcategoryId}/record")
    public ResponseEntity<Record> addRecord(@PathVariable Long subcategoryId, @RequestBody RequestAddRecord request) throws RecordNotFoundException {
        Subcategory subcategory = subcategoryService.getSubcategoryById(subcategoryId);
        Record record = new Record();
        record.setName(request.getName());
        record.setAmount(request.getAmount());
        record.setSubcategory(subcategory);
        Record recordResponse = recordService.createOrUpdateRecord(record);

        return ResponseEntity.ok(recordResponse);
    }
}
