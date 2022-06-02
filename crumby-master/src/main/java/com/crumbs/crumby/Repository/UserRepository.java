package com.crumbs.crumby.Repository;

import com.crumbs.crumby.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
