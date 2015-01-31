
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Akhil
 */
public class ShoppingCart {

    private ConcurrentHashMap<Long,Integer> cart;
    private double totalValue;
    private Market mkt;
    
    public ShoppingCart() {
        this.cart=new ConcurrentHashMap<>();
        this.mkt=new Market();
        this.totalValue = 0;
    }

    public ShoppingCart(Market mkt) {
        this.cart = new ConcurrentHashMap<>();
        this.totalValue = 0;
        this.mkt = mkt;
    }

    public boolean addToCart(Item itm,int qty){
        boolean added = false;
        if (itm != null && mkt.getItemByID(itm.getId()) != null) {
            if (cart.containsKey(itm.getId())) {
                cart.put(itm.getId(), cart.get(itm.getId()) + qty);
                added = true;
            } else {
                this.cart.put(itm.getId(), qty);
                added = true;
            }
        }
        if(added)
            updateCart();
        return added;
    }
    
    private void updateCart(){
        totalValue=0;
        for(Map.Entry<Long,Integer> e:cart.entrySet()){
            Item itm=mkt.getItemByID(e.getKey());
            totalValue+=e.getValue()*itm.getPrice();
        }
    }
    
    public Set<Long> getCartItems(){
        return cart.keySet();
    }
    
    public boolean removeItem(Item itm,int qty){
        boolean removed=false;
        if (itm !=null && mkt.getItemByID(itm.getId()) != null) {
            Integer cartQty = cart.get(itm.getId());
            if (cartQty != null) {
                if (cartQty > qty) {
                    cartQty -= qty;
                    cart.put(itm.getId(), cartQty);
                    removed = true;
                } else if (cartQty == qty) {
                    cart.remove(itm.getId());
                    removed = true;
                }
            }
            if(removed)
                updateCart();
        }
        return removed;
    }
    
    public String checkOut(){
        updateCart();
        double discount=mkt.getDiscounts(this);
        totalValue-=discount;
        StringBuilder sb=new StringBuilder();
        sb.append("Item\tQuantity\tPrice\tTotal\n");
        for(Map.Entry<Long,Integer> e:cart.entrySet()){
            Item itm=mkt.getItemByID(e.getKey());
            sb.append(itm.getName()).append("\t")
                .append(e.getValue()).append("\t")
                .append(itm.getPrice()).append("\t")    
                .append(e.getValue()*itm.getPrice()).append("\n");
        }
        sb.append("\n\t\tDiscounts\t").append(discount);
        sb.append("\n\t\tTotal\t").append(totalValue);
        return sb.toString();
    }
    
    public boolean hasItem(Item item){
        return cart.containsKey(item.getId());
    }
    
    public Integer itemQuantity(Item item){
        Integer qty=cart.get(item.getId());
        if(qty==null)
            qty=0;
        return qty;
    }
    
    public int cartCount(){
        return cart.size();
    }
    
    public double cartValue(){
        return totalValue;
    }
    
}
