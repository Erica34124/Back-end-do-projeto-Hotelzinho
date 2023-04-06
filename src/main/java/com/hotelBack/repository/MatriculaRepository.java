package com.hotelBack.repository;

import com.hotelBack.domain.InfoMatricula.Matricula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends MongoRepository<Matricula, String> {
}
