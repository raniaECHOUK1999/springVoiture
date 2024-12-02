package com.example.demo.repositories;

import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Trouver un utilisateur par email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);


    // Trouver un utilisateur par name
    @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> findByName(String name);

    // Requête personnalisée pour trouver les utilisateurs âgés de plus de 30 ans
    @Query("SELECT u FROM User u WHERE u.age > 30")
    List<User> findUsersOlderThan30();

    /**
     * Vérifie si un utilisateur existe par son username.
     * Utilisation d'une requête JPQL.
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.name = :username")
    boolean existsByUsername(@Param("username") String username);

    /**
     * Vérifie si un utilisateur existe par son email avec une requête personnalisée
      */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

}
