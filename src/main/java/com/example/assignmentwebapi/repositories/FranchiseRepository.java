package com.example.assignmentwebapi.repositories;

import com.example.assignmentwebapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
