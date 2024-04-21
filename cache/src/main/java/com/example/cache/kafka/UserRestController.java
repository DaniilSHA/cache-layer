package com.example.cache.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping
public class UserRestController
{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private UsersStorage usersStorage;

    @GetMapping("/notify")
    public void notifyUsers(@RequestParam("msg") String msg)
    {
        Set<Integer> allUserIds = usersStorage.getAllUserIds();
        allUserIds.forEach(id -> kafkaTemplate.send(KafkaCfg.TOPIC_CLIENT_NOTIFICATIONS, id + "^" + msg));
    }
}
