package revatureRevshop;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.RevShop.entity.buyers;

public class buyerTest {

	private buyers buyer;

    @Before
    public void setUp() {
        
        buyer = new buyers(
            "John Doe",
            "johndoe@example.com",
            "password123",
            1234567890L,
            "123 Main St",
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
        );
        buyer.setBuyerId(1); 
    }

    @Test
    public void testBuyerConstructorAndGetters() {
        assertEquals(1, buyer.getBuyerId());
        assertEquals("John Doe", buyer.getName());
        assertEquals("johndoe@example.com", buyer.getEmail());
        assertEquals("password123", buyer.getPassword());
        assertEquals(1234567890L, buyer.getPhoneNumber());
        assertEquals("123 Main St", buyer.getAddress());
        assertNotNull(buyer.getCreatedAt());
        assertNotNull(buyer.getUpdatedAt());
    }

    @Test
    public void testSetters() {
        buyer.setName("Jane Doe");
        assertEquals("Jane Doe", buyer.getName());
        
        buyer.setEmail("janedoe@example.com");
        assertEquals("janedoe@example.com", buyer.getEmail());

        buyer.setPassword("newpassword456");
        assertEquals("newpassword456", buyer.getPassword());

        buyer.setPhoneNumber(9876543210L);
        assertEquals(9876543210L, buyer.getPhoneNumber());

        buyer.setAddress("456 Elm St");
        assertEquals("456 Elm St", buyer.getAddress());
    }

    @Test
    public void testToString() {
        String expectedString = "Buyers{" +
                "buyerId=1" +
                ", name='John Doe'" +
                ", email='johndoe@example.com'" +
                ", password='password123'" +
                ", phoneNumber=1234567890" +
                ", address='123 Main St'" +
                ", createdAt=" + buyer.getCreatedAt() +
                ", updatedAt=" + buyer.getUpdatedAt() +
                '}';
        assertEquals(expectedString, buyer.toString());
    }

}
