
package controller;

import model.Product;
import view.ProductView;
import java.util.List;

public class ProductController {
    private List<Product> products;
    private ProductView view;
    
    public ProductController(List<Product> products, ProductView view){
        this.products = products;
        this.view = view;
    }
    
    public void displayProducts(){
        view.displayProducts(products);
    }
    
    public Product getProductById(String productId){
        return products.stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
    }
    
}
