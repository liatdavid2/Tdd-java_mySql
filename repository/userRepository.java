package com.group.db.springbootmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.db.springbootmysql.model.User;

public interface userRepository extends JpaRepository<User,Integer>{



}
