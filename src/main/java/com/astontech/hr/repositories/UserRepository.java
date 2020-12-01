package com.astontech.hr.repositories;

import com.astontech.hr.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
