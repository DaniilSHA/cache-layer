package com.example.cache.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class MyController
{
    @Autowired
    private AuthService authService;

    @Autowired
    private RedisConnector redisConnector;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean auth = authService.isAuth(user);
        if (!auth)
            throw new NotFoundUserException();
        return redisConnector.put(user.getUsername());
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader("auth") String sessionId) {
        redisConnector.remove(sessionId);
    }

    @GetMapping("/info")
    public String info(@RequestHeader("auth") String sessionId) {
        boolean auth = redisConnector.isAuth(sessionId);
        if (!auth)
            throw new NotAuthException();
        return "usefully packet";
    }
}
