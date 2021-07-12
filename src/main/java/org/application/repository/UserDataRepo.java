package org.application.repository;

import org.application.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Long> {

    @Transactional
    UserData deleteUserDataById(Long id);

//    UserData findById(Long id);
//    Long save(UserData user);
//
//    List<UserData> list();
//
//    boolean delete(Long id);
//
//    Optional<UserData> get(Long id);
//
//    Optional<UserData> updateIfPresent(Long id, UserData user);
}
