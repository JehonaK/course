package com.course.integration.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public final class SerializableNotification implements Serializable {

    private final String content;
    private final ArrayList<String> recipients;

    public SerializableNotification(@JsonProperty("content") String content,
                                    @JsonProperty("recipients") ArrayList<String> recipients) {
        this.content = content;
        this.recipients = recipients;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getRecipients() {
        return recipients;
    }

    @Override
    public String toString() {
        return "SerializableNotification{" +
                "content='" + content + '\'' +
                ", recipients=" + recipients +
                '}';
    }

}
