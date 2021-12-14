package Sedax.LoginComSpring.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Sedax.LoginComSpring.entities.User;

@Repository
public interface UsuarioRepo extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
    
//    @Query("SELECT u FROM user u WHERE u.username = :username")
//    User findUsername(String username);
    
    @Transactional
    @Modifying
    @Query("delete FROM User U WHERE U.username = ?1")
    void DeleteByusername(String username, String password);
    
    @Transactional
    @Modifying
    @Query("update User U set U.password=?2 where U.username=?1")
    void UpdatePassword(String username, String Password);
    
    Optional<User> findById(long id);
    
//    @Query("SELECT u FROM user u WHERE u.username = :username and u.password = :password")
//    Iterable<User> findByUsername(@Param(value = "username") String username,
//    		@Param(value = "password") String password);

}