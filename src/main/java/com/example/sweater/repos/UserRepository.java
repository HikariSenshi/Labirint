package com.example.sweater.repos;

import com.example.sweater.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    User findByActivationCode(String code);

}
