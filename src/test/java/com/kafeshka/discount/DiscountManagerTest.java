package com.kafeshka.discount;

import com.kafeshka.KafeshkaRS.discount.Discount;
import com.kafeshka.KafeshkaRS.discount.DiscountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kafeshka.KafeshkaRS.order.Order;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class DiscountManagerTest {

    private DiscountManager discountManager;
    @Mock
    private Order order;
@Mock
private Discount mockDiscount;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        List<Discount> availableDiscounts = new ArrayList<>();
        availableDiscounts.add(new Discount( "Over_50",10.00));
        availableDiscounts.add(new Discount( "Birthday", 20.00));
        availableDiscounts.add(mockDiscount);
        discountManager = new DiscountManager(availableDiscounts);
    }
    @Test
    void testApplyDiscountToOrder() {
        Discount discount = this.discountManager.getAvailableDiscounts().get(0);
        assertEquals("Over_50",discount.getName());
        assertEquals(10.00,discount.getDiscountAmount());
        when(order.getTotalAmount()).thenReturn(60.0);
        discountManager.applyDiscountToOrder(order, discount);
        verify(order).applyDiscount(6.00);
        when(order.getTotalAmount()).thenReturn(100.0);
        when(mockDiscount.getDiscountAmount()).thenReturn(50.00);
        discountManager.applyDiscountToOrder(order,mockDiscount);
        verify(order).applyDiscount(50.00);
    }

    @Test
    void testRemoveDiscountFromOrder() {
    this.discountManager.removeDiscountFromOrder(order);
    verify(order).removeDiscount();
    }

    @Test
    void testEditDiscount() {
        Discount existingDiscount = discountManager.getAvailableDiscounts().get(0);
        discountManager.editDiscount(existingDiscount, "new Discount", 20.0);
        assertEquals( 20.0, existingDiscount.getDiscountAmount());
        assertEquals( "new Discount",existingDiscount.getName());
    }
}
//TODO 1) re-write payment method class to "enum".
