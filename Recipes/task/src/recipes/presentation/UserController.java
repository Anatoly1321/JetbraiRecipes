package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.businessLayer.ForUsers.User;
import recipes.businessLayer.ForUsers.UserService;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        userService.save(user);
    }

}
