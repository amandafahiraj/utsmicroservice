package com.utscaseA.item_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utscaseA.item_service.model.Item;
import com.utscaseA.item_service.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Mendapatkan semua item
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Mendapatkan item berdasarkan id
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    // Membuat item baru
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Mengupdate item yang ada
    public Item updateItem(Long id, Item itemDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item tidak ditemukan dengan id " + id));
        item.setName(itemDetails.getName());
        item.setPrice(itemDetails.getPrice());
        item.setDescription(itemDetails.getDescription());
        item.setStock(itemDetails.getStock());
        return itemRepository.save(item);
    }

    // Menghapus item
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item tidak ditemukan dengan id " + id));
        itemRepository.delete(item);
    }
}
