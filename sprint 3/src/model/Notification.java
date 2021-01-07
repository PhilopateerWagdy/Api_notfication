package model;


public class Notification {
    private final int id;

    private final String subject;

    private final String content;

    private final LanguageEnum language;

    private final Type type;

    private final String user;




    public Notification( int id,
                        String subject,
                         String content,
                         LanguageEnum language,
                        Type type,
                         String user) {
        this.id = id;
        this.content = content;
        this.subject = subject;
        this.language = language;
        this.type=type;
        this.user = user;
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

    public Type getType() {
        return type;
    }


    public String getUser() {
        return user;
    }
}
