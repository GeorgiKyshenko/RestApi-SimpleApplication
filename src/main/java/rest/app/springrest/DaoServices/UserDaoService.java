package rest.app.springrest.DaoServices;

import org.springframework.stereotype.Component;
import rest.app.springrest.exceptions.UserNotFoundException;
import rest.app.springrest.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static long userCount = 0;

    static {
        users.add(new User(++userCount, "Georgi", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "John", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "Doe", LocalDate.now().minusYears(17)));
    }

    public List<User> findAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public User findById(long id) {
        User user;
        try {
            Predicate<? super User> predicate = user1 -> user1.getId() == id;
            user = users.stream().filter(predicate).findFirst().orElse(null);
        } catch (RuntimeException exception) {
            throw new UserNotFoundException("User with this ID does not exists");
        }
        return user;
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);

        return user;
    }

    public void deleteUser(long id) {
        try {
            Predicate<? super User> predicate = user -> user.getId() == id;
            users.removeIf(predicate);
        } catch (RuntimeException exception) {
            throw new UserNotFoundException("User with this ID does not exists");
        }
    }
}
