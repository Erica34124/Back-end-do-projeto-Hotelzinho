package com.hotelBack.repository;

import com.hotelBack.domain.InfoMatricula.Turma;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TurmaRepository extends MongoRepository<Turma, String> {
}
