package com.lin.demoes;

import com.lin.demoes.request.SubscribeRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController(value = "friends")
public class FriendController {

    private final UserService userService;

    public FriendController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/connect")
    public Map<String, Boolean> connect(@RequestBody Map<String, List<String>> request){
        List<String> friends = request.get("friends");
        // connect to friends
        return Map.of("success", true);
    }

    @PostMapping("/friends")
    public Map<String, Object> friends(@RequestBody Map<String, String> request){
        String email = request.get("email");
        // retrieve firends
        return Map.of("success", true,
                "friends", List.of(),
                "count", 1);
    }

    @PostMapping("/common")
    public Map<String, Object> common(@RequestBody Map<String, List<String>> request) {
        List<String> friends = request.get("friends");
        // find common friends
        return Map.of("success", true,
                "friends", List.of(),
                "count", 1);
    }

    @PostMapping("/subscribe")
    public Map<String, Object> subscribe(@RequestBody SubscribeRequest request) {
        String requestor = request.getRequestor();
        String target = request.getTarget();

        return Map.of("success", true);
    }

    @PostMapping("/block")
    public Map<String, Object> block(@RequestBody Map<String, String> request) {
        String requestor = request.get("requestor");
        String target = request.get("target");

        return Map.of("success", true);
    }

    @PostMapping("/send-message")
    public Map<String, Object> sendMessage(@RequestBody Map<String, String> request){
        String sender = request.get("sender");
        return Map.of("success", true,
                "recipients", List.of());
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Map<String, String> request){
        return Map.of("success", true,
                "email", List.of());
    }
}
