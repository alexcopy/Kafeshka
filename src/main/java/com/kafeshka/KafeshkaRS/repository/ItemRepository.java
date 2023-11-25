package com.kafeshka.KafeshkaRS.repository;

import com.kafeshka.KafeshkaRS.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ItemRepository extends JpaRepository<ItemModel, Long> {


}