package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.exception.DatabaseException;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.RecordRepository;
import org.hibernate.dialect.Database;
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

    public Record updateRecord(Record recordParam) throws RecordNotFoundException {

        Optional<Record> record = recordRepository.findById(recordParam.getId());

        if (record.isPresent()) {
            Record editRecord = record.get();
            double oldAmount = editRecord.getAmount();
            double newAmount = 0;
            if (recordParam.getAmount() != null) {
                editRecord.setAmount(recordParam.getAmount());
                newAmount = recordParam.getAmount();
                newAmount = newAmount - oldAmount;
            }
            if (recordParam.getName() != null) {
                editRecord.setName(recordParam.getName());
            }
            editRecord = recordRepository.save(editRecord);
            if (recordParam.getAmount() != null) {
                try {
                    subcategoryService.updateSubcategoryAmount(editRecord.getSubcategory().getId(), newAmount);
                } catch (RecordNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return editRecord;
        } else {
            throw new RecordNotFoundException("No record found for given id " + recordParam.getId());
        }
    }

    public boolean deteleRecord(Long id) {
        try {
            Optional<Record> record = recordRepository.findById(id);
            if (record.isPresent()) {
                recordRepository.deleteById(id);
                subcategoryService.updateSubcategoryAmount(record.get().getSubcategory().getId(), record.get().getAmount() * -1);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DatabaseException("Database exception");
        }
    }
}
