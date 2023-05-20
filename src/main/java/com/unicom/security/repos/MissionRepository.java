package com.unicom.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unicom.security.models.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>{

}
