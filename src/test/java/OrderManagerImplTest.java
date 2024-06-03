import org.example.Order;
import org.example.OrderManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderManagerImplTest {

    OrderManagerImpl ordManager;

    @BeforeEach
    public void setUp(){
        ordManager = new OrderManagerImpl();
    }

    //public void addOrder(Order order)
    @Test
    public void addOrder_ThanGetThisOrderbyId(){
        //Setup
        Order order = new Order(1,"101",120.50, "paid");
        //Execute
        ordManager.addOrder(order);
        //Assert
        assertEquals(order, ordManager.getOrderById(1));
    }

    //reikia pabaigti su Trow
    @Test
    public void addOrder_TryAddNullOrder(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        Order order3 = null;
        String expBool = null;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.addOrder(order3);
        System.out.println();
        //Assert
        assertEquals(expBool, ordManager.getOrderById(1));
    }

    //void removeOrder(int orderId);
    @Test
    public void removeOrder_AddTwoOrdersWithId1And2ThanRemoveOrderId1GetNullFromGetOrderById(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.removeOrder(order1.getId());
        //Assert
        assertEquals(null, ordManager.getOrderById(1));
    }

    @Test
    public void removeOrder_WithOrderIdNotFound(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.removeOrder(order2.getId());
        //Assert
        assertEquals(null, ordManager.getOrderById(2));
    }

    //Order getOrderById(int orderId);
    @Test
    public void getOrderById_AddTwoOrdersWithId1And2ThanGetOrder2(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(order2, ordManager.getOrderById(order2.getId()));
    }

    @Test
    public void getOrderById_AddTwoOrdersWithId1And2TryToGetOrderWithNonExistingId5GetNull(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(null, ordManager.getOrderById(5));
    }

    //List<Order> getOrdersByCustomer(String customerId)
    @Test
    public void getOrdersByCustomer_AddTwoOrders_GetListWithOrdersForCustomerId102(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        orderList.add(order2);
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(orderList, ordManager.getOrdersByCustomer(order2.getCustomerId()));
    }

    @Test
    public void getOrdersByCustomer_AddTwoOrders_TryToGetNonExistingCustomerOrderList_GetEmptyList(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(orderList, ordManager.getOrdersByCustomer("105"));
    }

    //double calculateTotalRevenue();
    @Test
    public void calculateTotalRevenue_calculateTwoOrdersAmountSum(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        double expValue = 169.50;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(expValue, ordManager.calculateTotalRevenue());
    }

    //boolean updateOrderStatus(int orderId, String status)
    @Test
    public void updateOrderStatus_testUpdateOrderId2StatusToUnpaidGetTrue(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        boolean expBool = true;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(expBool, ordManager.updateOrderStatus(2, "unpaid"));
    }

    @Test
    public void updateOrderStatus_TryUpdateNonExistingOrderId3GetFalse(){
        //Setup
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        boolean expBool = false;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        //Assert
        assertEquals(expBool, ordManager.updateOrderStatus(3, "unpaid"));
    }

    //List<Order> filterOrdersByStatus(String status)
    @Test
    public void filterOrdersByStatus_AddOrdersWithStatusPaidToListGetListWithOrders(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        Order order3 = new Order(3,"103",19.00, "unpaid");
        orderList.add(order1);
        orderList.add(order2);
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.addOrder(order3);
        //Assert
        assertEquals(orderList, ordManager.filterOrdersByStatus("paid"));
    }

    @Test
    public void filterOrdersByStatus_AddOrdersToListGetEmptyListByNonExistingStatus(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        Order order3 = new Order(3,"103",19.00, "unpaid");
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.addOrder(order3);
        //Assert
        assertEquals(orderList, ordManager.filterOrdersByStatus("preordered"));
    }

    //List<Order> getOrdersAboveCertainValue(double value)
    @Test
    public void getOrdersAboveCertainValue_AddOrdersToListGetOrdersListWithAmountAbove30(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        Order order3 = new Order(3,"103",19.00, "unpaid");
        orderList.add(order1);
        orderList.add(order2);
        double amountAbove = 30.00;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.addOrder(order3);
        //Assert
        assertEquals(orderList, ordManager.getOrdersAboveCertainValue(amountAbove));
    }

    @Test
    public void getOrdersAboveCertainValue_addOrdersToListGetEmptyListWhanCertainValueAbove130(){
        //Setup
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order(1,"101",120.50, "paid");
        Order order2 = new Order(2,"102",49.00, "paid");
        Order order3 = new Order(3,"103",19.00, "unpaid");
        double amountAbove = 130.00;
        //Execute
        ordManager.addOrder(order1);
        ordManager.addOrder(order2);
        ordManager.addOrder(order3);
        //Assert
        assertEquals(orderList, ordManager.getOrdersAboveCertainValue(amountAbove));
    }
}
