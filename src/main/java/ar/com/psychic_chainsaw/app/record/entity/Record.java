package ar.com.psychic_chainsaw.app.record.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Record {

    @Id
    private String id;
    private String key;
    private String value;

    public Record() {
    }

    public Record(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public Record setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Record setValue(String value) {
        this.value = value;
        return this;
    }
}
