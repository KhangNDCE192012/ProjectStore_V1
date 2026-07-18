package vn.edu.fpt.projectstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.projectstore.entity.Cart;
import vn.edu.fpt.projectstore.entity.Customer;
import vn.edu.fpt.projectstore.entity.GameAccount;
import vn.edu.fpt.projectstore.repository.CartRepositories;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepositories cartRepositories;

    public void addToCart(Customer customer,
                          GameAccount account,
                          Integer duration,
                          String rank,
                          int skin,
                          int level,
                          int vip) {

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setGameAccount(account);
        cart.setGame(account.getGame());
        cart.setPrice(account.getPrice());
        cart.setDuration(duration);
        cart.setRank(rank);
        cart.setSkin(skin);
        cart.setLovel(level);
        cart.setVip(vip);
        cart.setCreatedAt(LocalDateTime.now());

        cartRepositories.save(cart);
    }






    public Cart getCartById(UUID id){
        return cartRepositories.findByCartId(id);
    }

    public List<Cart> getCartsByCustomer(Customer customer){
        return cartRepositories.findByCustomer(customer);
    }

    public void delete(Cart cart){
        cartRepositories.delete(cart);
    }

    @Transactional
    public void deleteById(UUID cartId) {
        cartRepositories.deleteById(cartId);
    }


}
