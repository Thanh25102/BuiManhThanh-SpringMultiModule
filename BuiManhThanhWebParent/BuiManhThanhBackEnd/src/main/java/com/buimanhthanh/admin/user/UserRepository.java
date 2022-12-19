package com.buimanhthanh.admin.user;

import com.buimanhthanh.common.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>, PagingAndSortingRepository<User,Integer> {
    @Query("SELECT u from User u where u.email = :e")
    User getUserByEmail(@Param("e") String email);

    Long countById(Integer id);

    @Query("UPDATE User u set u.enabled =:e where u.id =:i")
    @Modifying
    void changeStatus(@Param("i") Integer id,@Param("e") Boolean enabled);
}
