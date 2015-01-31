import java.util.ArrayList;
import java.util.HashMap;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akhil
 */
public class ShoppingCartTest {
    
    ShoppingCart cart;
    Market mkt;
    
    public ShoppingCartTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mkt=new Market();
        cart=new ShoppingCart(mkt);
        mkt.addItem(new Item(1, "Nexus 5", "Google Nexus 5", 300.0));
        mkt.addItem(new Item(2, "i9100", "Samsung Galaxy s2", 250.0));
        mkt.addItem(new Item(3, "Nexus 10", "Google Nexus 10", 320.0));
        mkt.addItem(new Item(4, "Moto 360", "Motorola 360 Watch", 250.0));
        Discount d32=new DiscountRuleXForY(3, 2);
        Discount d36=new DiscountRuleXForYValue(2, 400.0);
        mkt.addItemDiscount(mkt.getItemByID(1), d32);
        mkt.addItemDiscount(mkt.getItemByID(2), d36);
        
        ArrayList<Item> setFree=new ArrayList<>();
        setFree.add(mkt.getItemByID(1));
        setFree.add(mkt.getItemByID(3));
        setFree.add(mkt.getItemByID(4));
        Discount d3FreeCheapest=new DiscountRuleXWithYFree(setFree);
        mkt.addItemDiscount(mkt.getItemByID(1), d3FreeCheapest);
        mkt.addItemDiscount(mkt.getItemByID(3), d3FreeCheapest);
        mkt.addItemDiscount(mkt.getItemByID(4), d3FreeCheapest);
        
        
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addTwoItemsToCart() {
        cart.addToCart(mkt.getItemByID(1), 2);
        cart.addToCart(mkt.getItemByID(3), 1);
        assertThat(cart.cartCount(), Is.is(2));
        assertTrue(cart.hasItem(mkt.getItemByID(1)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 10")));
    }
    
    @Test
    public void addTwoItemsAndCheckout() {
        cart.addToCart(mkt.getItemByID(1), 3);
        cart.addToCart(mkt.getItemByID(3), 1);
        
        System.out.println(cart.checkOut());
        
        assertThat(cart.cartCount(), Is.is(2));
        assertTrue(cart.hasItem(mkt.getItemByID(1)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 10")));
        assertThat(cart.cartValue(), Is.is(920.0));
    }
    
    @Test
    public void addItems3ForPiceAndCheckout() {
        
        
        cart.addToCart(mkt.getItemByID(2), 6);
        cart.addToCart(mkt.getItemByID(1), 2);
        
        System.out.println(cart.checkOut());
        
        assertThat(cart.cartCount(), Is.is(2));
        assertTrue(cart.hasItem(mkt.getItemByID(2)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 5")));
        assertThat(cart.cartValue(), Is.is(1800.0));
    }
    
    @Test
    public void addItems3CheapestFreeAndCheckout() {
        
        cart.addToCart(mkt.getItemByID(1), 2);
        cart.addToCart(mkt.getItemByID(3), 1);
        cart.addToCart(mkt.getItemByID(4), 1);
        
        System.out.println(cart.checkOut());
        
        assertThat(cart.cartCount(), Is.is(3));
        assertTrue(cart.hasItem(mkt.getItemByID(3)));
        assertTrue(cart.hasItem(mkt.getItemByID(4)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 5")));
        assertThat(cart.cartValue(), Is.is(920.0));
    }
    
    @Test
    public void addItems1Get1OtherFreeAndCheckout() {
        HashMap<Item,Integer> set1Free=new HashMap<>();
        set1Free.put(mkt.getItemByID(4),1);
        Discount d3Free1=new DiscountRuleNXWithKYFree(set1Free,2);
        mkt.addItemDiscount(mkt.getItemByID(1), d3Free1);
        mkt.addItemDiscount(mkt.getItemByID(2), d3Free1);
        mkt.addItemDiscount(mkt.getItemByID(3), d3Free1);
        
        cart.addToCart(mkt.getItemByID(1), 2);
        cart.addToCart(mkt.getItemByID(2), 1);
        
        System.out.println(cart.checkOut());
        assertThat(cart.cartCount(), Is.is(3));
        assertTrue(cart.hasItem(mkt.getItemByID(2)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 5")));
        assertThat(cart.cartValue(), Is.is(850.0));
    }
    
    @Test
    public void addItems1Get1OtherFreeAndCheckout2() {
        HashMap<Item,Integer> set1Free=new HashMap<>();
        set1Free.put(mkt.getItemByID(4),1);
        Discount d3Free1=new DiscountRuleNXWithKYFree(set1Free,2);
        mkt.addItemDiscount(mkt.getItemByID(1), d3Free1);
        mkt.addItemDiscount(mkt.getItemByID(2), d3Free1);
        mkt.addItemDiscount(mkt.getItemByID(3), d3Free1);
        
        cart.addToCart(mkt.getItemByID(1), 2);
        cart.addToCart(mkt.getItemByID(2), 1);
        cart.addToCart(mkt.getItemByID(4), 1);
        System.out.println(cart.checkOut());
        
        assertThat(cart.cartCount(), Is.is(3));
        assertTrue(cart.hasItem(mkt.getItemByID(2)));
        assertTrue(cart.hasItem(mkt.getItemByName("Nexus 5")));
        assertThat(cart.cartValue(), Is.is(1100.0));
    }
    
}
