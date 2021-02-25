package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    SubcategoryService subcategoryService;

    public Record createOrUpdateRecord(Record recordParam) {

        if (recordParam.getId() != null) {
            Optional<Record> record = recordRepository.findById(recordParam.getId());

            if (record.isPresent()) {
                Record editRecord = record.get();
                editRecord.setAmount(recordParam.getAmount());
                editRecord.setName(recordParam.getName());

                editRecord = recordRepository.save(editRecord);
                return editRecord;
            } else {
                recordParam = recordRepository.save(recordParam);
                try {
                    subcategoryService.updateSubcategoryAmount(recordParam.getSubcategory().getId(), recordParam.getAmount());
                } catch (RecordNotFoundException e) {
                    e.printStackTrace();
                }
                return recordParam;
            }
        } else {
            recordParam = recordRepository.save(recordParam);
            try {
                subcategoryService.updateSubcategoryAmount(recordParam.getSubcategory().getId(), recordParam.getAmount());
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
            return recordParam;
        }
    }
}
