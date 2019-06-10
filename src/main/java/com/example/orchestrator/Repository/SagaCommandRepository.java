package com.example.orchestrator.Repository;

import com.example.orchestrator.Model.SagaCommand;
import com.example.orchestrator.Model.SagaStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SagaCommandRepository extends JpaRepository<SagaCommand, Integer> {


    public SagaCommand findSagaCommandByCommand(String name);
}
