
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akhil
 */
public class DiscountRuleXWithYFree implements Discount{

    List<Item> set;

    public DiscountRuleXWithYFree() {
        set=new ArrayList<>();
    }

    public DiscountRuleXWithYFree(ArrayList<Item> set) {
        this.set = set;
    }
       
    @Override
    public double getDiscount(Item itm,ShoppingCart cart) {
        double disc=0.0;
        double cheapest=0;
        int min=0;
        int allItems=0;
        for(Item item:set){
            if(cart.hasItem(item)){
                int qty=cart.itemQuantity(item);
                if(min==0 || min>qty)
                    min=qty;
                if(cheapest==0 || cheapest>item.getPrice())
                    cheapest=item.getPrice();
                allItems++;
            }
        }
        if(allItems==set.size() && min>0 && cheapest>0){
            disc = cheapest * min;
        }
        return disc;
    }

    @Override
    public List<Item> getRelatedDiscountItems() {
        return new ArrayList<>(set);
    }
    
}
