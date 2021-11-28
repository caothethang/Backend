package com.garden.root.repositories;

import com.garden.root.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree,Long> {
}
