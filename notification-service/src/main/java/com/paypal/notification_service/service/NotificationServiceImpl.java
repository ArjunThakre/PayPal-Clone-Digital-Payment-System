package com.paypal.notification_service.service;

import com.paypal.notification_service.entity.Notification;
import com.paypal.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository repository;

    @Override
    public Notification sendNotification(Notification notification) {
        notification.setSentAt(LocalDateTime.now());
        return repository.save(notification);
    }

    @Override
    public List<Notification> getNotificationByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
