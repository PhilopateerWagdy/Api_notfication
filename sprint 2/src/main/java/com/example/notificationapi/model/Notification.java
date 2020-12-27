package com.example.notificationapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class Notification {
    private final int id;

    private final String subject;
    @NonNull
    private final String content;
    @NonNull
    private final LanguageEnum language;
    @NonNull
    private final Type type;
    @NonNull
    private final String user;

    private final boolean status;



    public Notification(@JsonProperty("id") int id,
                        @JsonProperty("subject") String subject,
                        @JsonProperty("content") String content,
                        @JsonProperty("language") LanguageEnum language,
                        @JsonProperty("type") Type type,
                        @JsonProperty("user") String user,
                        boolean status) {
        this.id = id;
        this.content = content;
        this.subject = subject;
        this.language = language;
        this.type=type;
        this.user = user;
        this.status=status;
    }


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    @NonNull
    public Type getType() {
        return type;
    }


    @NonNull
    public String getUser() {
        return user;
    }

    public boolean isStatus() {
        return status;
    }
}
