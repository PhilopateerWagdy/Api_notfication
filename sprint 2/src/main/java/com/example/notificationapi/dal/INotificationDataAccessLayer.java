package com.example.notificationapi.dal;

import com.example.notificationapi.model.Notification;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface INotificationDataAccessLayer {
    public int AddNotification(Notification notification) throws SQLException;
    public boolean DeleteNotification(int id);
    public int UpdateNotification(int id , Notification notification);
    public Optional<Notification> GetNotificationByID(int id);
    public List<Notification> GetNotification() throws SQLException;
    public int sendNotification(int id);
}