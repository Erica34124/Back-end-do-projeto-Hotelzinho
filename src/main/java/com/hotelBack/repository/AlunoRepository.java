package com.hotelBack.repository;

import com.hotelBack.domain.InfoAluno.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
