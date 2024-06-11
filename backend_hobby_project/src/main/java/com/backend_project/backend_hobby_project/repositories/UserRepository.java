package com.backend_project.backend_hobby_project.repositories;


import com.backend_project.backend_hobby_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
