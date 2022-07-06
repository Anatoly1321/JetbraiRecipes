package recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import recipes.businessLayer.ForUsers.User;

public interface UserRepository extends CrudRepository<User, String> {
}

