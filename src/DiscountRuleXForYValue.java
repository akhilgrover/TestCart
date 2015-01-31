
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Akhil
 */
public class DiscountRuleXForYValue implements Discount{

    int x;
    double y;

    public DiscountRuleXForYValue() {
        this.x=3;
        this.y=250;
    }

    public DiscountRuleXForYValue(int x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public double getDiscount(Item itm,ShoppingCart cart) {
        double disc=0.0;
        int qty=cart.itemQuantity(itm);
        if(qty>=x){
            int cnts = qty / x;
            disc = ((itm.getPrice() * x) - y) * cnts;
        }
        return disc;
    }

    @Override
    public List<Item> getRelatedDiscountItems() {
        return new ArrayList<>();
    }
    
}
