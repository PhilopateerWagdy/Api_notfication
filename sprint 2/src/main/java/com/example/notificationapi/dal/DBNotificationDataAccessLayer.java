package com.example.notificationapi.dal;

import com.example.notificationapi.model.LanguageEnum;
import com.example.notificationapi.model.Notification;
import com.example.notificationapi.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        if (n.isEmpty()||n.get().isStatus()==true)
            return 0;
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
        String sql="SELECT id, subject, content, Language, type, user,status FROM notification_db.notification WHERE id = ?";
        Notification notification = jdbcTemplate.queryForObject
                (sql, new Object[]{id} ,
                (resultSet, i) -> {
                    int NotificationID=resultSet.getInt("id");
                    String NotificationSubject =resultSet.getString("subject");
                    String NotificationContent =resultSet.getString("content");
                    LanguageEnum languageEnum =LanguageEnum.valueOf(resultSet.getString("Language"));
                    Type  type= Type.valueOf(resultSet.getString("type"));
                    String user = resultSet.getString("user");
                    boolean status=resultSet.getBoolean("status");
                    return new Notification(NotificationID,
                            NotificationSubject,
                            NotificationContent,
                            languageEnum,
                            type, user, status);
                });
        return Optional.ofNullable( notification);

    }

    @Override
    public List<Notification> GetNotification() {
        String sql="select id, subject,content ,Language,type ,user,status from notification_db.notification";

        List<Notification> notifications = jdbcTemplate.query(sql, (resultSet, i) -> {
            int NotificationID = Integer.parseInt(resultSet.getString("id"));
            String NotificationSubject = resultSet.getString("subject");
            String NotificationContent = resultSet.getString("content");
            LanguageEnum languageEnum = LanguageEnum.valueOf(resultSet.getString("Language"));
            Type  type= Type.valueOf(resultSet.getString("type"));
            String user = resultSet.getString("user");
            boolean status=resultSet.getBoolean("status");;
            return new Notification(NotificationID, NotificationSubject,
                    NotificationContent, languageEnum,type, user, status);
        });

        return notifications;

    }

    @Override
    public int sendNotification(int id) {
        Optional<Notification> notification = GetNotificationByID(id);
        if (notification==null)
            return 0;
        String sql = "UPDATE notification_db.notification SET status=? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,true,id);

        if (notification.get().getType()==Type.sms)
        {
            sql = " insert into smsnotification (id, subject, content, Language ,user)"
                    + " values ( ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(
                    sql,
                    notification.get().getId(),
                    notification.get().getSubject(),
                    notification.get().getContent(),
                    notification.get().getLanguage().toString(),
                    notification.get().getUser()
            );
        }
        else {
                sql = " insert into mailnotification (id, subject, content, Language ,user)"
                    + " values ( ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(
                    sql,
                    notification.get().getId(),
                    notification.get().getSubject(),
                    notification.get().getContent(),
                    notification.get().getLanguage().toString(),
                    notification.get().getUser()
            );
        }

    }
}
