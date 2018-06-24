package com.phpbae.jpa.dao.repository;

import com.phpbae.jpa.business.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

    @Query("SELECT p FROM Part p")
    public List<Part> getParts();
}
