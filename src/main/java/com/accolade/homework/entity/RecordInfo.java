package com.accolade.homework.entity;

import java.time.LocalDateTime;

/**
 * @author Roman Svoboda
 */
public class RecordInfo {

    private RecordStatus recordStatus;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime deleted;
    private String recordData;

    public RecordInfo(){}

    public RecordInfo(RecordStatus recordStatus, LocalDateTime created, LocalDateTime updated, LocalDateTime deleted, String recordData) {
        this.recordStatus = recordStatus;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.recordData = recordData;
    }

    public static RecordInfo createNew(String recordData) {
        return new RecordInfo(RecordStatus.NEW, LocalDateTime.now(), null, null, recordData);
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
    }
}
