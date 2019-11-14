package edu.upc.dsa;

import edu.upc.dsa.models.Product;

import java.util.List;


public interface ProductsManager {

    public void addProduct(String id, int amount, double price);
    public Product addProduct (Product product);
    public int size();
    public List<Product> getProducto();
    public Product getProductById(String id);
    public int numSalesByProductId(String productName);
    public List<Product> productsOrderedByPrice();
    public List<Product> productsOrderedBynumSales();
    public void deleteProduct(String id);
    public Product updateProductById(Product product);
}
