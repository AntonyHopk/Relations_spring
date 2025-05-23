package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
//    Optional<User> findById(int id);
//    List<User> findAll();
//    User create(User user);
//    void delete(int id);

//    Iterable<User> findAllByNameLikeIgnoreCase(String name);
//
//
//    @Query(value="select * from list.t_users where c_name like :name",nativeQuery = true)
//    Iterable<User> findAllByLastNameLikeIgnoreCase(String name);
//
//    @Query(value="select u from User u where u.name like :name")//jpql
//    Iterable<User> findAllByAgeLikeIgnoreCase(String name);
//
//    @Query(name = "User.FindUser")//named query
//    Iterable<User> aboba(String name);

}
