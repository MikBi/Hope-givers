package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entities.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findTopById(Integer id);

    User findByEmail(String email);

    @Query("select u from User u where u.id <> ?1 ")
    List<User> findWithoutMe(int id);

    User findUsersByAdminId(int id);

    @Query("select count(u) from User u")
    Integer amountOfUsers();

    @Query("select u from User u where u.role.id = 2")
    List<User> allAdmins();
}
