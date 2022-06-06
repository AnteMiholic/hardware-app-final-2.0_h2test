package hr.tvz.miholic.hardwareapp.Security.repository;


import hr.tvz.miholic.hardwareapp.Security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

}
