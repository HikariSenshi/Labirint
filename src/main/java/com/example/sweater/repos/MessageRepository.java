package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

@SuppressWarnings("unused")
public interface MessageRepository extends MongoRepository<Message, BigInteger> {

    List<Message> findByTag(String tag);


}
