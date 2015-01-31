
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akhil
 */
public class DiscountRuleNXWithKYFree implements Discount{

    HashMap<Item,Integer> set;
    int n;
    
    public DiscountRuleNXWithKYFree() {
        set=new HashMap<>();
        n=1;
    }

    public DiscountRuleNXWithKYFree(HashMap<Item, Integer> set, int n) {
        this.set = set;
        this.n = n;
    }
       
    @Override
    public double getDiscount(Item itm,ShoppingCart cart) {
        double disc=0.0;
        int qty=cart.itemQuantity(itm);
        int q=qty/n;
        for(Item item:set.keySet()){
            cart.addToCart(item, q*set.get(item));
            disc+=item.getPrice()*q*set.get(item);
        }
        return disc;
    }

    @Override
    public List<Item> getRelatedDiscountItems() {
        return new ArrayList<>(set.keySet());
    }
    
}
