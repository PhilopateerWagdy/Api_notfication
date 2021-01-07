package com.example.notificationapi.dal;

import com.example.notificationapi.model.LanguageEnum;
import com.example.notificationapi.model.Notification;
import com.example.notificationapi.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("MySQLDb")
public class DBNotificationDataAccessLayer implements INotificationDataAccessLayer {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBNotificationDataAccessLayer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public int AddNotification(Notification notification)  {
        String sql = " insert into notification ( subject, content, Language,type ,user,status)"
                + " values ( ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                notification.getSubject(),
                notification.getContent(),
                notification.getLanguage().toString(),
                notification.getType().toString(),
                notification.getUser(),
                false
        );

    }

    @Override
    public boolean DeleteNotification(int id) {
        Optional<Notification> notification = GetNotificationByID(id);
        if (notification.isEmpty())
            return false;
//        if (notification.get().getType()==Type.sms){
//            String sql = "delete from notification_db.smsnotification where id = ?";
//            jdbcTemplate.update(sql, id);
//        }
//        else
//        {
//            String sql = "delete from notification_db.mailnotification where id = ?";
//            jdbcTemplate.update(sql, id);
//        }
        String sql = "delete from notification_db.notification where id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    @Override
    public int UpdateNotification(int id, Notification notification) {
        Optional<Notification> n = GetNotificationByID(id);
//        if (n.get().getType()==Type.sms){
//            if (notification.getType()==Type.sms) {
//                String sql = "UPDATE notification_db.smsnotification SET subject=?, content=?,Language=?,user=?" +
//                        "WHERE id = ?";
//                jdbcTemplate.update(sql, notification.getSubject(),
//                        notification.getContent(),
//                        notification.getLanguage().toString(),
//                        notification.getUser()
//                        , id);
//            }
//            else{
//                String sql = "delete from notification_db.smsnotification where id = ?";
//                jdbcTemplate.update(sql, id);
//                sql = " insert into mailnotification (id, subject, content, Language ,user)"
//                        + " values ( ?, ?, ?, ?, ?)";
//
//                jdbcTemplate.update(
//                    sql,
//                    id,
//                    notification.getSubject(),
//                    notification.getContent(),
//                    notification.getLanguage().toString(),
//                    notification.getUser()
//                );
//            }
//        }
//        else
//        {
//            if (notification.getType()==Type.mail) {
//                String sql = "UPDATE notification_db.mailnotification SET subject=?," +
//                        " content=?,Language=?,user=? " +
//                        "WHERE id = ?";
//                jdbcTemplate.update(sql, notification.getSubject(),
//                        notification.getContent(),
//                        notification.getLanguage().toString(),
//                        notification.getUser()
//                        , id);
//            }
//            else{
//                String sql = "delete from notification_db.mailnotification where id = ?";
//                jdbcTemplate.update(sql, id);
//                sql = " insert into smsnotification (id, subject, content, Language ,user)"
//                        + " values ( ?, ?, ?, ?, ?)";
//                jdbcTemplate.update(
//                        sql,
//                        id,
//                        notification.getSubject(),
//                        notification.getContent(),
//                        notification.getLanguage().toString(),
//                        notification.getUser()
//                );
//            }
//        }
        String sql = "UPDATE notification_db.notification SET subject=?" +
                ", content=?,Language=?,type =? ,user=?" +
                "WHERE id = ?";
        return jdbcTemplate.update(sql,notification.getSubject(),
                notification.getContent(),
                notification.getLanguage().toString(),
                notification.getType().toString(),
                notification.getUser()
                ,id);
    }

    @Override
    public Optional<Notification> GetNotificationByID(int id) {
        String sql="SELECT id, subject, content, Language, type, user FROM notification_db.notification WHERE id = ?";
        Notification notification = jdbcTemplate.queryForObject
                (sql, new Object[]{id} ,
                (resultSet, i) -> {
                    int NotificationID=resultSet.getInt("id");
                    String NotificationSubject =resultSet.getString("subject");
                    String NotificationContent =resultSet.getString("content");
                    LanguageEnum languageEnum =LanguageEnum.valueOf(resultSet.getString("Language"));
                    Type  type= Type.valueOf(resultSet.getString("type"));
                    String user = resultSet.getString("user");

                    return new Notification(NotificationID,
                            NotificationSubject,
                            NotificationContent,
                            languageEnum,
                            type, user);
                });
        return Optional.ofNullable( notification);

    }

    @Override
    public List<Notification> GetNotification() {
        String sql="select id, subject,content ,Language,type ,user from notification_db.notification";

        List<Notification> notifications = jdbcTemplate.query(sql, (resultSet, i) -> {
            int NotificationID = Integer.parseInt(resultSet.getString("id"));
            String NotificationSubject = resultSet.getString("subject");
            String NotificationContent = resultSet.getString("content");
            LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
            Type  type= Type.valueOf(resultSet.getString("type"));
            String user = resultSet.getString("user");
            return new Notification(NotificationID, NotificationSubject,
                    NotificationContent, languageEnum,type, user);
        });

        return notifications;

    }

    @Override
    public boolean sendNotification(Notification notification) {
        String sql="SELECT id, subject, content, Language, type, user FROM notification_db.notification WHERE subject = ?";
        Notification notificationTemplate;
        try {
            notificationTemplate = jdbcTemplate.queryForObject
                    (sql, new Object[]{notification.getSubject()} ,
                            (resultSet, i) -> {
                                int NotificationID=resultSet.getInt("id");
                                String NotificationSubject =resultSet.getString("subject");
                                String NotificationContent =resultSet.getString("content");
                                LanguageEnum languageEnum =LanguageEnum.valueOf(resultSet.getString("Language"));
                                Type  type= Type.valueOf(resultSet.getString("type"));
                                String user = resultSet.getString("user");
                                return new Notification(NotificationID,
                                        NotificationSubject,
                                        NotificationContent,
                                        languageEnum,
                                        type, user);
                            });
            if (notificationTemplate==null)
                return false;

        }
        catch (Exception e){
            return false;
        }

        int dataSize= notification.getContent().length();
        String content = notificationTemplate.getContent();
        String data[] =notification.getContent().split(" ");

        int count = 0,safetyCount=0;

        for (int i = 0; i < content.length() - 1; i++) {
            if (content.charAt(i)=='{') {
                if(safetyCount<dataSize){
                    safetyCount++;
                }
                else{
                    return false;
                }
                content = content.substring(0,i)+data[count++]+content.substring(i+3);
            }
        }
        notification.setContent(content);

        if (notification.getType()==Type.sms)
        {
            sql = " insert into smsnotification (id, subject, content, Language ,user)"
                    + " values ( ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    notification.getId(),
                    notification.getSubject(),
                    notification.getContent(),
                    notification.getLanguage().toString(),
                    notification.getUser()
            );
            return true;
        }
        else {
                sql = " insert into mailnotification (id, subject, content, Language ,user)"
                    + " values ( ?, ?, ?, ?, ?)";
                jdbcTemplate.update(
                        sql,
                        notification.getId(),
                        notification.getSubject(),
                        notification.getContent(),
                        notification.getLanguage().toString(),
                        notification.getUser()
                );
                return true;
        }

    }

    @Override
    public List<Notification> GetNotificationOfUser(String user) {
        String sql;
        List<Notification> mailNotifications;
        List<Notification> smsNotifications;

        sql="SELECT id, subject, content, Language, user FROM notification_db.mailnotification WHERE user = ?";
        mailNotifications = jdbcTemplate.query(sql,new Object[]{user}, (resultSet, i) -> {
                int NotificationID = Integer.parseInt(resultSet.getString("id"));
                String NotificationSubject = resultSet.getString("subject");
                String NotificationContent = resultSet.getString("content");
                LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
                String userT = resultSet.getString("user");
                return new Notification(NotificationID, NotificationSubject,
                        NotificationContent, languageEnum, Type.mail, userT);
            });
        sql="SELECT id, subject, content, Language, user FROM notification_db.smsnotification WHERE user = ?";
        smsNotifications = jdbcTemplate.query(sql,new Object[]{user}, (resultSet, i) -> {
            int NotificationID = Integer.parseInt(resultSet.getString("id"));
            String NotificationSubject = resultSet.getString("subject");
            String NotificationContent = resultSet.getString("content");
            LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
            String userT = resultSet.getString("user");
            return new Notification(NotificationID, NotificationSubject,
                    NotificationContent, languageEnum, Type.sms, userT);
        });
        List<Notification> newList = Stream.concat(mailNotifications.stream(), smsNotifications.stream())
                .collect(Collectors.toList());
        return newList;
    }

    @Override
    public List<Notification> GetNotificationsOfUsers() {

        String sql="select id, subject,content ,Language ,user from notification_db.mailnotification";

        List<Notification> mailNotifications = jdbcTemplate.query(sql, (resultSet, i) -> {
            int NotificationID = Integer.parseInt(resultSet.getString("id"));
            String NotificationSubject = resultSet.getString("subject");
            String NotificationContent = resultSet.getString("content");
            LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
            String user = resultSet.getString("user");
            return new Notification(NotificationID, NotificationSubject,
                    NotificationContent, languageEnum,Type.mail, user);
        });
        sql="select id, subject,content ,Language ,user from notification_db.smsnotification";
        List<Notification> smsNotifications = jdbcTemplate.query(sql, (resultSet, i) -> {
            int NotificationID = Integer.parseInt(resultSet.getString("id"));
            String NotificationSubject = resultSet.getString("subject");
            String NotificationContent = resultSet.getString("content");
            LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
            String user = resultSet.getString("user");
            return new Notification(NotificationID, NotificationSubject,
                    NotificationContent, languageEnum,Type.sms, user);
        });
        List<Notification> newList = Stream.concat(mailNotifications.stream(), smsNotifications.stream())
                .collect(Collectors.toList());
        return newList;
    }
    public void DeleteNotificationsOfUser(String user){
        List<Notification>notifications= GetNotificationOfUser(user);
        String sql ;
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getType()==Type.mail){
                sql= "delete from notification_db.mailnotification where id = ?";
                jdbcTemplate.update(sql, notifications.get(i).getId());
            }
            else{
                sql= "delete from notification_db.smsnotification where id = ?";
                jdbcTemplate.update(sql, notifications.get(i).getId());
            }
        }
    }
}
