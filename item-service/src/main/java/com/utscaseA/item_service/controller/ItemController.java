package com.utscaseA.item_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utscaseA.item_service.model.Item;
import com.utscaseA.item_service.service.ItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemController {

   @Autowired
   private ItemService itemService;

   // Endpoint untuk mengambil semua item
   @GetMapping
   public List<Item> getAllItems() {
       return itemService.getAllItems();
   }

   // Endpoint untuk mengambil item berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Item> getItemById(@PathVariable Long id) {
       return itemService.getItemById(id)
               .map(item -> ResponseEntity.ok().body(item))
               .orElse(ResponseEntity.notFound().build());
   }

   // Endpoint untuk membuat item baru
   @PostMapping
   public ResponseEntity<Item> createItem(@RequestBody Item item) {
       Item createdItem = itemService.createItem(item);
       return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
   }

   // Endpoint untuk mengupdate item yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
       try {
           Item updatedItem = itemService.updateItem(id, itemDetails);
           return ResponseEntity.ok(updatedItem);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
   }

   // Endpoint untuk menghapus item
   @DeleteMapping("/{id}")
   public ResponseEntity<Map<String, String>> deleteItem(@PathVariable Long id) {
       try {
           itemService.deleteItem(id);
           Map<String, String> response = new HashMap<>();
           response.put("message", "Item berhasil dihapus");
           return ResponseEntity.ok(response);
       } catch (RuntimeException e) {
           Map<String, String> response = new HashMap<>();
           response.put("message", "Item tidak ditemukan dengan id " + id);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }
   }
}
