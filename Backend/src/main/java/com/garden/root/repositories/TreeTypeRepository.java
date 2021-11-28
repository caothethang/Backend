package com.garden.root.repositories;

import com.garden.root.entity.TreeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeTypeRepository extends JpaRepository<TreeType,Long> {

}
