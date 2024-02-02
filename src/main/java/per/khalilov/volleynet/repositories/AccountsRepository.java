package per.khalilov.volleynet.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.khalilov.volleynet.models.Account;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountsRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByName(String name);
}
