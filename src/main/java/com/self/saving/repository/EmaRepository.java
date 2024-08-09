package com.self.saving.repository;

import com.self.saving.entity.EmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmaRepository extends JpaRepository<EmaEntity, String> {
}
