package com.utscaseA.item_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utscaseA.item_service.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
