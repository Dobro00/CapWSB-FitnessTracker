package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param fragment email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */

    @Query("SELECT new pl.wsb.fitnesstracker.user.api.UserEmailDto(u.id, u.email) FROM User u WHERE lower(u.email) LIKE lower(concat('%', :fragment, '%'))")
    List<UserEmailDto> findByEmail(String fragment);

    @Query("SELECT u FROM User u WHERE u.birthdate < :time")
    List<User> getOlderUsers(LocalDate time);
}
