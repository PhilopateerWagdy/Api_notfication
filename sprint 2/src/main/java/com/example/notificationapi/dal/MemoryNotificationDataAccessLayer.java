package com.example.notificationapi.dal;

import com.example.notificationapi.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("MemoryDao")
public class MemoryNotificationDataAccessLayer implements INotificationDataAccessLayer {
    private static List<Notification> notifications =new ArrayList<>();
    private static int id=0;
    @Override
    public int AddNotification(Notification notification) {
        notifications.add(new Notification(id++,
                notification.getSubject(),
                notification.getContent(),
                notification.getLanguage(),
                notification.getType(),notification.getUser(), false));
        return 1;
    }

    @Override
    public boolean DeleteNotification(int id) {
        Optional<Notification> notification = GetNotificationByID(id);
        if (notification.isEmpty())
            return false;
        notifications.remove(notification.get());
        return true;
    }

    @Override
    public int UpdateNotification(int id, Notification notification) {
        return GetNotificationByID(id)
                .map(notification1 -> {
                    int indexOfNotification = notifications.indexOf(notification1);
                    if (indexOfNotification>=0){
                        notifications.set(id, new Notification( id,notification.getSubject(),
                                notification.getContent(),notification.getLanguage(),
                                notification.getType(),notification.getUser(), false));
                        return 1;
                    }
                    else
                        return 0;
                }).orElse(0);
    }

    @Override
    public Optional<Notification> GetNotificationByID(int id) {
        return notifications.stream()
                .filter(notification -> notification.getId()==id)
                .findFirst();
    }

    @Override
    public List<Notification> GetNotification() {
        return notifications;
    }

    @Override
    public int sendNotification(int id) {
        return 0;
    }
}
