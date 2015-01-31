
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Akhil
 * 
 * Discount Rule for buy 3 items and pay for 2
 */
public class DiscountRuleXForY implements Discount{

    int x;
    int y;

    public DiscountRuleXForY() {
        this.x=3;
        this.y=2;
    }
    
    /**
     * 
     * @param x  X items 
     * @param y  for price of Y
     */
    public DiscountRuleXForY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public double getDiscount(Item itm,ShoppingCart cart) {
        double disc=0.0;
        int qty=cart.itemQuantity(itm);
        if(qty>=x){
            int cnts=qty/x;
            disc = itm.getPrice() * (x-y) * cnts;
            System.out.println(disc);
        }
        return disc;
    }

    @Override
    public List<Item> getRelatedDiscountItems() {
        return new ArrayList<>();
    }
    
}
