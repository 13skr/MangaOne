package ru.mangaone.mangaone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mangaone.mangaone.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    void deleteUserById(Long id);
}
