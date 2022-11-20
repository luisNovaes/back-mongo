package com.mongo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.model.GroceryItem;
import com.mongo.repository.ItemRepository;

@RestController
public class MongoController {
	
	@Autowired
	ItemRepository repository;
	
    public MongoController(ItemRepository groceryItemRepo) {
		this.repository = groceryItemRepo;
	}

	private void createGroceryItems() {
        System.out.println("Data creation started...");
        repository.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        repository.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        repository.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        repository.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        repository.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println("Data creation complete...");
    }
    
 // READ
    // 1. Show all the data
	@GetMapping("/")
     public void showAllGroceryItems() {
         
         repository.findAll().forEach(item -> System.out.println(getItemDetails(item)));
     }
     
     // 2. Get item by name
     public void getGroceryItemByName(String name) {
         System.out.println("Getting item by name: " + name);
         GroceryItem item = repository.findItemByName(name);
         System.out.println(getItemDetails(item));
     }
     
     // 3. Get name and quantity of a all items of a particular category
     public void getItemsByCategory(String category) {
         System.out.println("Getting items for the category " + category);
         List<GroceryItem> list = repository.findAll(category);
         
         list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
     }
     
     // 4. Get count of documents in the collection
     public void findCountOfGroceryItems() {
         long count = repository.count();
         System.out.println("Number of documents in the collection: " + count);
     }
     
 // Print details in readable form
     
     public String getItemDetails(GroceryItem item) {

         System.out.println(
         "Item Name: " + item.getName() + 
         ", \nQuantity: " + item.getQuantity() +
         ", \nItem Category: " + item.getCategory()
         );
         
         return "";
     }
     
 public void updateCategoryName(String category) {
         
         // Change to this new value
         String newCategory = "munchies";
         
         // Find all the items with the category snacks
         List<GroceryItem> list = repository.findAll(category);
         
         list.forEach(item -> {
             // Update the category in each document
             item.setCategory(newCategory);
         });
         
         // Save all the items in database
         List<GroceryItem> itemsUpdated = repository.saveAll(list);
         
         if(itemsUpdated != null)
             System.out.println("Successfully updated " + itemsUpdated.size() + " items.");         
     }
 
	//DELETE
	 public void deleteGroceryItem(String id) {
	     repository.deleteById(id);
	     System.out.println("Item with id " + id + " deleted...");
	 }

	 
	 public void run(String... args) {
	        
	        System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
	        
	        createGroceryItems();
	        
	        System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
	        
	        showAllGroceryItems();
	        
	        System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
	        
	        getGroceryItemByName("Whole Wheat Biscuit");
	        
	        System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
	        
	        getItemsByCategory("millets");
	    
	          System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
	        
	        updateCategoryName("snacks");    
	                
	        System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
	        
	        deleteGroceryItem("Kodo Millet");
	        
	        System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
	        
	        findCountOfGroceryItems();
	        
	        System.out.println("\n-------------------THANK YOU---------------------------");
	        
	    }
}
