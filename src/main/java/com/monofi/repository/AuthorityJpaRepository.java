package com.monofi.repository;

import com.monofi.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends JpaRepository<Authority,Long> {
}
