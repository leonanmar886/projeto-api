package com.projeto.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.projetoapi.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{}
