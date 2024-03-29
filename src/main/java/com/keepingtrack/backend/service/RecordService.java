package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.exception.DatabaseException;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    SubcategoryService subcategoryService;

    public Record createRecord(Record recordParam) {

        recordParam = recordRepository.save(recordParam);
        try {
            subcategoryService.updateSubcategoryAmount(recordParam.getSubcategory().getId(), recordParam);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return recordParam;

    }

    public Record createAutoGeneratedDebtRecord(Record record) {

        record = recordRepository.save(record);
        subcategoryService.updateAutoGeneratedDebtSubcategoryAmount(record.getSubcategory(), record);
        return record;
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
                    Record recordTemp = new Record();
                    recordTemp.setName(editRecord.getName());
                    recordTemp.setAmount(newAmount);
                    subcategoryService.updateSubcategoryAmount(editRecord.getSubcategory().getId(), recordTemp);
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
                record.get().setAmount(record.get().getAmount() * -1);
                subcategoryService.updateSubcategoryAmount(record.get().getSubcategory().getId(), record.get());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DatabaseException("Database exception");
        }
    }
}
