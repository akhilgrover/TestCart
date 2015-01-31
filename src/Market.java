
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Akhil
 */
public class Market {
    
    final private ConcurrentHashMap<Long,Item> items;
    final private ConcurrentHashMap<Long,ArrayList<Discount>> discounts;

    public Market() {
        items=new ConcurrentHashMap<>();
        discounts=new ConcurrentHashMap<>();
    }

    public Market(ConcurrentHashMap<Long,Item> items) {
        this.items = items;
        discounts=new ConcurrentHashMap<>();
    }
    
    public void addItem(Item itm){
        if(!this.items.containsKey(itm.getId())){
            items.put(itm.getId(),itm);
        }
    }
    
    public Collection<Item> getIterator(){
        return items.values();
    }
    
    public Item getItemByID(long id){
        return items.get(id);
    }
    
    public Item getItemByName(String name){
        for(Item itm:items.values()){
            if(itm.getName().equals(name))
                return itm;
        }
        return null;
    }
    
    public int getItemsInMarket(){
        return items.size();
    }
    
    public void addItemDiscount(Item itm,Discount disc){
        if(!items.containsKey(itm.getId()))
            return;
        ArrayList<Discount> arr=discounts.get(itm.getId());
        if(arr==null){
            arr=new ArrayList<>();
            discounts.put(itm.getId(), arr);
        }
        arr.add(disc);
    }

    double getDiscounts(ShoppingCart cart) {
        double disc=0.0;
        ArrayList<Item> doneRelated=new ArrayList<>();
        for(Long itmId:cart.getCartItems()){
            Item itm=items.get(itmId);
            ArrayList<Discount> arr=discounts.get(itmId);
            if(arr!=null && !doneRelated.contains(itm)){
                for(Discount d:arr){
                    double appliedDisc=d.getDiscount(itm, cart);
                    if(appliedDisc>0){
                        disc+=appliedDisc;
                        doneRelated.addAll(d.getRelatedDiscountItems());
                        break;
                    }
                }
            }
        }
        return disc;
    }
}
