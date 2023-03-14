package rest.app.springrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.app.springrest.models.User2JPA;

@Repository
public interface UserRepository2JPA extends JpaRepository<User2JPA, Long> {
}
