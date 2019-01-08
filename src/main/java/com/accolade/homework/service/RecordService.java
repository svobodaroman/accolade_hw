package com.accolade.homework.service;

import com.accolade.homework.entity.Record;
import com.accolade.homework.entity.RecordInfo;
import com.accolade.homework.persist.RecordPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Roman Svoboda
 */
@Service
public class RecordService {

    private RecordPersist recordPersist;

    @Autowired
    public RecordService(RecordPersist recordPersist) {
        this.recordPersist = recordPersist;
    }

    public Record createNew(String recordData) {

        final UUID uuid = UUID.randomUUID();
        final RecordInfo recordInfo = RecordInfo.createNew(recordData);
        final Record record = new Record(uuid.toString(), recordInfo);
        recordPersist.insertNew(record);
        return record;
    }

    public Record getOneByUid(String uuid) throws IOException {
        return recordPersist.getOneByUuid(uuid);
    }

    public void deleteByUuid(String uuid) throws IOException {
        recordPersist.deleteByUuid(uuid);
    }

    public void updateByUuid(String uuid) throws IOException {
        recordPersist.updateByUuid(uuid);
    }
}
