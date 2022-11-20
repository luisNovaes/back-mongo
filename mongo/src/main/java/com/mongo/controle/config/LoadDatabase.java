package com.mongo.controle.config;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongo.model.GroceryItem;
import com.mongo.model.Product;
import com.mongo.repository.ItemRepository;
import com.mongo.repository.ProductRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository product) {

    return args -> {

    	//long id, @NotBlank @Size(max = 100) String name, String descriptio
    	
   product.save(new Product(1L, "Porta copo", "Porta copo de madeira"));
   product.save(new Product(2L, "Prato", "Redonco blindex"));
    	
     /* groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
      groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
      groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
      groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
      groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
      
      groceryItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));*/
    };
  }
  
	//Print details in readable form
	  
	  public String getItemDetails(GroceryItem item) {
	
	      System.out.println(
	      "Item Name: " + item.getName() + 
	      ", \nQuantity: " + item.getQuantity() +
	      ", \nItem Category: " + item.getCategory()
	      );
	      
	      return "";
	  }
}
