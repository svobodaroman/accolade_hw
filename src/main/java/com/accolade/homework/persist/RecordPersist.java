package com.accolade.homework.persist;

import com.accolade.homework.entity.Record;
import com.accolade.homework.entity.RecordStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author Roman Svoboda
 */
@Service
public class RecordPersist {

    public static final String RECORD_ID = "recordId";
    private ObjectMapper objectMapper;
    private File file = new File("database.json");

    @Autowired
    public RecordPersist(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public void insertNew(Record record)  {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            JsonGenerator generator = objectMapper
                    .getFactory()
                    .createGenerator(fileOutputStream);

            objectMapper.writeValue(generator, record);
            generator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUuid(String uuid) throws IOException {
        Iterator<JsonNode> recordNodes = getRecordNodes();
        JsonNode node = findRecordNodeWithUuid(uuid, recordNodes);
        Record oneByUuid = getOneByUuid(uuid);
        oneByUuid.getInfo().setDeleted(LocalDateTime.now());
        oneByUuid.getInfo().setRecordStatus(RecordStatus.DELETED);
        objectMapper.updateValue(node, oneByUuid);
    }

    public Record getOneByUuid(String uuid) throws IOException {
        Iterator<JsonNode> recordNodes = getRecordNodes();
        JsonNode node = findRecordNodeWithUuid(uuid, recordNodes);
        return objectMapper.convertValue(node, Record.class);
    }

    private JsonNode findRecordNodeWithUuid(String uuid, Iterator<JsonNode> nodes) {
        while(nodes.hasNext()){
            JsonNode node = nodes.next();
            if (uuid.equals(node.get(RECORD_ID).asText())) {
                return node;
            }
        }
        return null;
    }

    private Iterator<JsonNode> getRecordNodes() throws IOException {
        JsonNode rootNode = getRootNode();
        return rootNode.elements();
    }

    private JsonNode getRootNode() throws IOException {
        byte[] jsonData = Files.readAllBytes(file.toPath());
        return objectMapper.readTree(jsonData);
    }

    public void updateByUuid(String uuid) throws IOException {
        Iterator<JsonNode> recordNodes = getRecordNodes();
        JsonNode node = findRecordNodeWithUuid(uuid, recordNodes);
        Record oneByUuid = getOneByUuid(uuid);
        oneByUuid.getInfo().setUpdated(LocalDateTime.now());
        oneByUuid.getInfo().setRecordStatus(RecordStatus.UPDATED);
        objectMapper.updateValue(node, oneByUuid);
    }
}
