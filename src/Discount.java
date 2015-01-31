
import java.util.List;




/**
 *
 * @author Akhil
 */
public interface Discount {

    public double getDiscount(Item itm,ShoppingCart cart);
    
    public List<Item> getRelatedDiscountItems();
    
    
}
