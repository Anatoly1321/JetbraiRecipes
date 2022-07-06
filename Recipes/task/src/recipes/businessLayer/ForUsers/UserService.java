package recipes.businessLayer.ForUsers;

import myExceptions.RecipeDoesNotExistException;
import myExceptions.UserWithSpecifiedEmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.persistance.UserRepository;

import javax.xml.bind.ValidationException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public void save(User user) {
        if (findUserByEmail(user.getEmail()) != null) {
            throw new UserWithSpecifiedEmailAlreadyExistException("User with specified email already exist");
        }

        userRepository.save(user);
    }

}
