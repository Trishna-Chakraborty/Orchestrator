package com.example.orchestrator.Repository;

import com.example.orchestrator.Model.ServiceHostMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceHostMappingRepository extends JpaRepository<ServiceHostMapping,Integer> {
    @Query("SELECT host FROM ServiceHostMapping s WHERE s.serviceName = :servicename")
    public String getServiceHostMappingByServiceName(@Param("servicename") String servicename);
}
