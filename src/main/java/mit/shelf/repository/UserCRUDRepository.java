package mit.shelf.repository;

import mit.shelf.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCRUDRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where id = :id",nativeQuery = true)
    User findUserById(Long id);
}
