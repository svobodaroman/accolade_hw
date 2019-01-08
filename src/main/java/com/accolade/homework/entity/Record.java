package com.accolade.homework.entity;


/**
 * @author Roman Svoboda
 */
public class Record {

    private String recordId;
    private RecordInfo info;

    public Record(){
    }

    public Record(String recordId, RecordInfo info) {
        this.recordId = recordId;
        this.info = info;
    }

    public String getRecordId() {
        return recordId;
    }

    public RecordInfo getInfo() {
        return info;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setInfo(RecordInfo info) {
        this.info = info;
    }
}
