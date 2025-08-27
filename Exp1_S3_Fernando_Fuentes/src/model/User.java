
package model;

public class User {
    private String userId;
    private String name;
    private String email;
    private ShoppingCart cart;
    
    public User(String userId, String name, String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.cart = ShoppingCart.getInstance();
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public ShoppingCart getCart() { return cart; }
    
    @Override
    public String toString(){
        return name + " (" + email + ")";
    }
}
