package capgemini.gameshop.entity;

import java.util.List;

public class Cart {

    private long id;
    private User user;
    private List<Product> listProduct;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public void addProductToCart (Product product) {
        listProduct.add(product);
    }
}
