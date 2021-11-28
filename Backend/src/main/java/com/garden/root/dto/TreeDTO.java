package com.garden.root.dto;

import com.garden.root.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeDTO extends JpaRepository<Tree, Long> {
}
