package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
