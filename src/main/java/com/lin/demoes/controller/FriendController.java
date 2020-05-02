package com.lin.demoes.controller;

import com.lin.demoes.service.UserService;
import com.lin.demoes.model.User;
import com.lin.demoes.request.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController(value = "friends")
public class FriendController {

    private final UserService userService;

    public FriendController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/connect")
    public Map<String, Boolean> connect(@RequestBody ConnectRequest request){
        List<String> friends = request.getFriends();

        User user1 = userService.findByEmail(friends.get(0));
        User user2 = userService.findByEmail(friends.get(1));

        user1.getFriends().add(user2.getEmail());
        user2.getFriends().add(user1.getEmail());
        userService.save(user1);
        userService.save(user2);

        return Map.of("success", true);
    }

    @PostMapping("/friends")
    public Map<String, Object> friends(@RequestBody FriendRequest request){
        String email = request.getEmail();
        User user = userService.findByEmail(email);
        return Map.of("success", true,
                "friends", user.getFriends(),
                "count", user.getFriends().size());
    }

    @PostMapping("/common")
    public Map<String, Object> common(@RequestBody CommonFriendRequest request) {
        List<String> friends = request.getFriends();
        // find common friends
        User user1 = userService.findByEmail(friends.get(0));
        User user2 = userService.findByEmail(friends.get(1));

        Collection<String> commonFriends = CollectionUtils.intersection(user1.getFriends(), user2.getFriends());

        // user1.getFriends(), user2.getFriends()
        return Map.of("success", true,
                "friends", commonFriends,
                "count", 1);
    }

    @PostMapping("/subscribe")
    public Map<String, Object> subscribe(@RequestBody SubscribeRequest request) {
        String requestor = request.getRequestor();
        String target = request.getTarget();
        User user = userService.findByEmail(request.getRequestor());
        user.getSubscribes().add(request.getTarget());

        return Map.of("success", true);
    }

    @PostMapping("/block")
    public Map<String, Object> block(@RequestBody BlockRequest request) {
        User user = userService.findByEmail(request.getRequestor());
        user.getBlocks().add(request.getTarget());
        // TODO not so sure should remove from friends and subscriber list
        return Map.of("success", true);
    }

    @PostMapping("/send-message")
    public Map<String, Object> sendMessage(@RequestBody MessageRequest request){
        User user = userService.findByEmail(request.getSender());

        Collection<String> allCollections = CollectionUtils.union(user.getSubscribes(), user.getFriends());
        Collection<String> total = CollectionUtils.subtract(allCollections, user.getBlocks());

        return Map.of("success", true,
                "recipients", new HashSet<>(total));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Map<String, String> request){
        return Map.of("success", true,
                "email", List.of());
    }
}
