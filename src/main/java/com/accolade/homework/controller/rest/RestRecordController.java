package com.accolade.homework.controller.rest;

import com.accolade.homework.entity.Record;
import com.accolade.homework.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Roman Svoboda
 */
@RestController
@RequestMapping("/record")
public class RestRecordController {

    private RecordService recordService;

    @Autowired
    public RestRecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping(value = "/{uuid}",
            produces = "application/json"
    )
    public Record getRecordByUid(@PathVariable(value = "uuid") String uuid) throws IOException {
        return recordService.getOneByUid(uuid);
    }

    @PostMapping(value = "/",
            produces = "application/json")
    public Record postRecordData(String recordData){
        Record newRecord = recordService.createNew(recordData);
        return newRecord;
    }

    @RequestMapping(value = "/{uuid}",
            method = RequestMethod.DELETE,
            produces = "application/json"
    )
    public void deleteRecord(@PathVariable("uuid") String uuid) throws IOException {
        recordService.deleteByUuid(uuid);
    }

    @RequestMapping(value = "/{uuid}",
            method = RequestMethod.PATCH,
            produces = "application/json"
    )
    public void updateRecord(@PathVariable("uuid") String uuid) throws IOException {
        recordService.updateByUuid(uuid);
    }

    @ExceptionHandler(IOException.class)
    public String ioExceptionHandler(){
        return "Error";
    }
}
