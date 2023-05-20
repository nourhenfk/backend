package com.unicom.security.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicom.security.models.FormateurExterne;

public interface FormateurExRepository extends JpaRepository<FormateurExterne, Long> {
	Optional<FormateurExterne> findById(Long id);
}
