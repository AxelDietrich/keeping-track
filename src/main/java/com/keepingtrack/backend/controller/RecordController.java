package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.entities.Subcategory;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.request.RequestAddRecord;
import com.keepingtrack.backend.request.RequestUpdateRecord;
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

    @PutMapping("/records/{recordId}")
    public ResponseEntity updateRecord(@PathVariable Long recordId, @RequestBody RequestUpdateRecord request) throws RecordNotFoundException {
        Record record = new Record();
        record.setId(recordId);
        record.setAmount(request.getAmount());
        record.setName(request.getName());

        recordService.updateRecord(record);


        return ResponseEntity.ok("Record successfully updated");
    }

    @DeleteMapping("/records/{recordId}")
    public ResponseEntity deleteRecord(@PathVariable Long recordId) {
        boolean response = recordService.deteleRecord(recordId);
        if (response) {
            return ResponseEntity.ok("Record successfully deleted");
        } else {
            return ResponseEntity.ok("No record found for given id " + recordId);
        }
    }
}
