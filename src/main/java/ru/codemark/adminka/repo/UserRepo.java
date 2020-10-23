package ru.codemark.adminka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.codemark.adminka.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
