
import java.util.*;

/* In this exercise we model a sales management system of a store (Like Amazon). 
In designing this system, we  implement customer management, warehouse management and customer purchase management in a simple way.
The way this system communicates with its users is with input commands in the terminal, and our program reads input in an infinite loop. 
The command format of this system is hierarchical. In this way, the type of command (add, remove, report, etc.) is determined at first. 
Then the entity on which this command is supposed to be applied (good, customer or repository and...). Finally, the entity information is entered. 
The program ends with the terminate command.

-------------------------------------------------------
add command:

If this command is entered, in the next line, the user enters the required entity.

If the entity is a customer, in the following lines, the user enters the customer's details as follows.
Customer ID
Customer Name

If the good is in stock, the user enters the good details in the following lines:
Good ID
Godd Name
The price of good
The number of goods to be added

If it is a warehouse, in the next lines, the user enters the warehouse details as follows.
Warehouse ID
Warehouse capacity

order :
Purchase request ID
ID of the requester

balance :
is used to add to customer balance . In the following lines, the balance specifications are entered as follows.
Customer ID
The amount to be added to the customer's balance

items :
This command is for adding goods to the customer's order. In the next lines , item specifications are entered as follows .
Purchase request ID
Product ID
Number of goods

discount :
If there is a discount, the user enters the details of the discount in the following lines .
Discount ID
discount percent
-------------------------------------------------------

report command:

customers:

In this case, the customer information of the store should be printed in one line. 
The output format of each client is as follows.

customer-ID, customer-name, customer-balance, total-order-size, submitted-size

repositories:

In this case, the information of each warehouse should be printed in one line with the following format.

repository-ID, repository-capacity, repository-free-capacity

income:

This command prints the store's revenue on the next line.

-------------------------------------------------------

remove command:

items:

This command removes an item from the customer's shopping list. The input of the following lines to describe this command is as follows.

Request ID

Product ID
-------------------------------------------------------

submit command:

order:

This command registers a order. The next line entry to describe this command is as follows.

Purchase ID

discount:

This order is for applying discounts to purchases. The input of the following lines to describe this command is as follows.

Request ID

Discount ID

Note: If the user uses this command, the discount will be lost and will not affect other requests.

*/

class Shop {
	
	#This class is used to model the store in general .

    private String name;
    private ArrayList<Customer> customers;
    private HashMap<Integer,Customer>customerHashMap;
    private ArrayList<Respiratory> respiratories;
    private HashMap<Integer,Respiratory>respiratoryHashMap;
    private HashMap<Integer,Good> goodHashMap;
    private ArrayList<Good>goods;
    private HashMap<Integer,Discount> discountHashMap;
    private ArrayList<Discount>discounts;
    private HashMap<Good, Integer> goodsSold;
    private int income;

    public Discount getdiscount(int id){

        return this.discountHashMap.get(id);

    }

    public int getincome() {
        int income = 0;
        for (int i = 0; i < this.goods.size(); i++) {
            if (this.goodsSold.containsKey(this.goods.get(i))) {
                income += this.goodsSold.get(this.goods.get(i)) * this.goods.get(i).getPrice();
            }
        }
        return income;
}

    public int getIncome() {
        return income;
    }

    public Good getgood(int id){

        return this.goodHashMap.get(id);
    }

    public void addGoodsSold(Good good,int amount) {
        if (this.goodsSold.containsKey(good)) this.goodsSold.put(good, this.goodsSold.get(good) + amount);
        else this.goodsSold.put(good,amount);

    }

    public Customer getCustomer(int id) {
        return this.customerHashMap.get(id);
    }

    public Shop(String name){
		# Receives the name of the store as input .

        this.name = name;
        this.customers = new ArrayList<>();
        this.goods = new ArrayList<>();
        this.respiratories = new ArrayList<>();
        this.discounts = new ArrayList<>();
        this.goodsSold = new HashMap<>();
        this.customerHashMap=new HashMap<>();
        this.discountHashMap=new HashMap<>();
        this.goodHashMap=new HashMap<>();
        this.respiratoryHashMap=new HashMap<>();
    }

    public void addCustomer(Customer customer) {
		#This function adds a customer to the list of store customer members.
        this.customers.add(customer);
        this.customerHashMap.put(customer.getId(),customer);
    }

    public ArrayList<Customer> customerList() {
        return this.customers;
    }

    public void addRespiratory(Respiratory respiratory) {
		#Adds a warehouse to the store warehouse list .
        this.respiratories.add(respiratory);
        this.respiratoryHashMap.put(respiratory.getId(),respiratory);
    }

    public ArrayList<Respiratory> respiratoryList() {
        return this.respiratories;

    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addGood(Good good) {
		#Adds a new product to the list of store products .
        this.goods.add(good);
        this.goodHashMap.put(good.getId(),good);
    }

    public void incrementGood(Good good, int ammount) {
	/* 	Adds an item to a warehouse with the number of item. 
		The addition of goods is done in such a way that the tanks are checked in order of their capacity ascending.
		Any warehouse that has more than the amount of empty capacity, we will add the goods to that warehous. */
		
        ArrayList<Respiratory> shopRespiratories = this.respiratoryList();
        Respiratory min = null;
        for (int i = 0, minimum = 0; i < shopRespiratories.size(); i++) {
            if (minimum == 0 && shopRespiratories.get(i).getCapacity() >= ammount) {
                min = shopRespiratories.get(i);
                minimum++;
            }
            if (minimum != 0 && shopRespiratories.get(i).getCapacity() >= ammount && shopRespiratories.get(i).getCapacity() < min.getCapacity())
                min = shopRespiratories.get(i);

        }
        if (min != null) {
            min.addgood(good, ammount);
            min.setCapacity(min.getCapacity() - ammount);
        }
        //else System.out.println("Not enough space");
    }

    public ArrayList<Good> getgoods() {

        return this.goods;

    }

    public HashMap<Good, Integer> getitemsSold() {
		#One hashmap Returns containing all items as key along with number sold as value.
        return this.goodsSold;
    }

    public void addDisount(Discount discount) {
		# discount adds to the list of store discounts .
        this.discounts.add( discount);
        this.discountHashMap.put(discount.getId(),discount);
    }

}

class Good{
	# is used to describe the goods sold in the store.
    private String name;
    private int id;
    private int price;
    private Shop shop;

    public Good(String name,int id,int price){
		#Receives product name, product ID and price .
        this.name=name;
        this.id=id;
        this.price=price;

    }

    public Good(String name,int id,int price,Shop shop){
        this.name=name;
        this.id=id;
        this.price=price;
        this.shop = shop;
        this.shop.addGood(this);

    }

    public Shop getShop() {
        return shop;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

}
class Respiratory{
    #models store warehouses 
    private int id;
    private int capacity;
    private int maxcapacity;
    private HashMap<Good,Integer>goods;
    private Shop shop;

    public Respiratory(int id,int capacity,Shop shop){
		#This constructor method takes the ID and capacity of the warehouse .
        this.capacity=capacity;
        this.id=id;
        this.maxcapacity=capacity;
        this.goods=new HashMap<>();
        this.shop = shop;
        this.shop.addRespiratory(this);
    }

    public Shop getShop() {
        return shop;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getfreecapacity() {
		#This function returns the empty capacity of the warehouse .
        return maxcapacity;
    }

    public HashMap<Good,Integer>getgood(){
		#One hashMap It returns that the key is the goods in the warehouse and their value is equal to the number of each goods .
        return this.goods;
    }

    public void setGoods(Good good,Integer amount){
        this.getgood().put(good,amount);
    }

    public void removegood(Good good,int amount){
        this.goods.put(good,this.goods.get(good)-amount);
    }

    public void addgood(Good good,int amount){
        if(this.goods.containsKey(good)) {
            this.goods.put(good, this.goods.get(good) + amount);
        }
        else this.goods.put(good,amount);
    }

}

class Customer {
	#This class is used to model store customers .
    private Shop shop;
    private int id;
    private String name;
    private int balance;
    private ArrayList<Discount> discounts;
    private HashMap<Integer,Discount>discountHashMap;
    private ArrayList<Order> orders;
    static private HashMap<Integer,Order>orderHashMap;
    private ArrayList<Order> pendingorders;
    private ArrayList<Order> submitedorders;

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public void addOrderHashmap(Order order){
        orderHashMap.put(order.getId(),order);
    }

    public Order getorder(int id) {
        if(orderHashMap.containsKey(id)) {
            //System.out.println("the id of order is"+orderHashMap.get(id).getId());
            return orderHashMap.get(id);
        }
        else{
            //System.out.println("couldn,t find the order");
            return null;
        }

    }


    public Discount getDiscount(int id) {
        return this.discountHashMap.get(id);
    }

    public Customer(int id, String name, Shop shop) {
		# takes the name of the customer and its ID and the shop the customer is a member of
        this.id = id;
        this.name = name;
        this.balance = 0;
        this.discounts = new ArrayList<>();
        this.discountHashMap=new HashMap<>();
        this.orders = new ArrayList<>();
        orderHashMap=new HashMap<>();
        this.pendingorders = new ArrayList<>();
        this.submitedorders = new ArrayList<>();
        this.shop = shop;
        this.shop.addCustomer(this);

    }

    public Shop getShop() {
        return shop;
    }

    public void addDiscout(Discount discount) {
        this.discounts.add(discount);
        this.shop.addDisount(discount);
        this.discountHashMap.put(discount.getId(),discount);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
		# Returns the amount of the customer's inventory .
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addOrder(Order order) {
		# Adds a pending order and order to the list of orders and pending orders
        this.orders.add(order);
        this.pendingorders.add(order);
        orderHashMap.put(order.getId(),order);

    }

    public ArrayList<Order> getPendingorders() {
        return this.pendingorders;
    }

    public ArrayList<Order> getSubmitedorders() {
		# Returns all pending orders that the user has logged .
        return this.submitedorders;
    }

    public void removediscount(Discount discount) {
        if(discount!=null && this.discounts.contains(discount) && this.discountHashMap.containsKey(discount.getId())) {
            this.discounts.remove(discount);
            this.discountHashMap.remove(discount.getId());
        }

    }

    public int submitorder(Order order) {
		
	/* 	Registers a pending request. If his inventory was less than the price of the pending order or the product was not available in the quantity
		he requested, the user's request will not be registered. When the user registers the pending order, the amount of the purchase request must be deducted
		from his balance. Then, the number of goods ordered in the request is deducted from the warehouse goods. 
		If the item in the request is available in several warehouses more than the requested number, 
		it will be selected in the warehouse whose identifier is less and the requested number will be reduced from the warehouse balance. 
		After registering the request, the user has no right to change the products in his list . */
		
        int minimum;
        int i;
        int j;
        int k;
        int f;
        HashMap<Good, Integer> orderGoodsAmount = order.getGoodsAmount();
        ArrayList<Good> shopGoods = this.shop.getgoods();
        ArrayList<Respiratory> shopRespiratories = this.shop.respiratoryList();
        Respiratory min = null;
        //System.out.println("the precentage of discount is"+order.getDiscount().getPrecent());
        //System.out.println("the price of order is"+order.calculatePrice());
        if (this.balance < order.calculatePrice()){
            //System.out.println("order not sufficient");
            return 0;
        }
        else {
            for (i = 0; i < shopGoods.size(); i++) {
                if (orderGoodsAmount.containsKey(shopGoods.get(i))) {
                    //System.out.println("passed contains key level");
                    for (k = 0, minimum = 0; k < shopRespiratories.size(); k++) {
                        /*if (minimum > 0 && shopRespiratories.get(k).getgood().containsKey(shopGoods.get(i)) && shopRespiratories.get(k).getgood().get(shopGoods.get(i)) >= orderGoodsAmount.get(shopGoods.get(i))) {
                            if (shopRespiratories.get(k).getgood().get(shopGoods.get(i)) < min.getgood().get(shopGoods.get(i))) {
                                min = shopRespiratories.get(k);
                                //System.out.println("founded better minimum");
                            }
                        }*/
                        if (minimum == 0 && shopRespiratories.get(k).getgood().containsKey(shopGoods.get(i)) && shopRespiratories.get(k).getgood().get(shopGoods.get(i)) >= orderGoodsAmount.get(shopGoods.get(i))) {
                            min = shopRespiratories.get(k);
                            minimum++;
                            //System.out.println("founded minimum");
                        }

                        if (minimum!=0)break;
                    }
                    if (minimum == 0) {
                        //System.out.println("we don,t have enough item to support your order");
                        return 0;
                    }
                    min.addgood(shopGoods.get(i), -order.getGoodsAmount().get(shopGoods.get(i)));
                    min.setCapacity(min.getCapacity() + order.getGoodsAmount().get(shopGoods.get(i)));
                }
            }

        }
        this.pendingorders.remove(order);
        submitedorders.add(order);
        order.setStatus("passed");
        this.setBalance(this.getBalance() - order.calculatePrice());
        this.shop.setIncome(this.shop.getIncome()+order.calculatePrice());
        Discount discardedDiscount=order.getDiscount();
        Customer customer=order.getCustomer();
        customer.removediscount(discardedDiscount);
        for( i=0;i<shopGoods.size();i++){
            if (orderGoodsAmount.containsKey(shopGoods.get(i))) {
                this.shop.addGoodsSold(shopGoods.get(i),orderGoodsAmount.get(shopGoods.get(i)));
            }

        }
        return 1;

    }

}

class Order{
    # This class is used to model the customer's purchase request .
    private int id;
    private Customer customer;
    private String status;
    private HashMap<Good,Integer>goodsAmount;
    private Discount discount;

    public Discount getDiscount() {
        return discount;
    }

    public HashMap<Good, Integer> getGoodsAmount() {
        return goodsAmount;
    }

    public Order(int id,Customer customer){
		# Receives the ID of the purchase request and the requesting customer 
        this.id=id;
        this.status="pending";
        this.goodsAmount=new HashMap<>();
        this.discount=null;
        customer.addOrderHashmap(this);

    }

    public Order(int id,Customer basecustomer,Customer customer){
        this.id=id;
        this.status="pending";
        this.goodsAmount=new HashMap<>();
        this.discount=null;
        basecustomer.addOrderHashmap(this);
        customer.addOrder(this);
        this.customer=customer;

    }

    public Integer getgoodamount(Good good){
        return this.goodsAmount.get(good);
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
		# Returns the status of the request . The status of the request should be pending at first . If the user registers it, its status should be changed to submitted .
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void additem(Good good,int ammount) {
		# Adds good to the shopping list based on the amount entered

        if (this.goodsAmount.containsKey(good)) {
            this.goodsAmount.put(good, this.goodsAmount.get(good)+ammount);
            }
        else {
            this.goodsAmount.put(good, ammount);
        }
    }

    public void removeitem(Good good){
        //System.out.println(this.goodsAmount);
        if(this.goodsAmount.containsKey(good)){
            this.goodsAmount.remove(good);
            //System.out.println("remove was succesful");
        }
        /*ArrayList<Good>shopgoods=this.customer.getShop().getgoods();
        for(int i=0;i<shopgoods.size();i++){
            if(this.goodsAmount.containsKey(shopgoods.get(i)))System.out.println(shopgoods.get(i).getName());
        }*/
        //System.out.println(this.goodsAmount);

    }

    public HashMap<Good, Integer> getItems() {
/* 		The output of this function is one HashMap is that the keys are the goods in the shopping list 
		and the values are the number of those goods in the order */
        return goodsAmount;
    }

    public int calculatePrice(){
/* 		Returns the cost of the order . This amount is equal to the total price of the goods multiplied by 
		the number of each item in the order . If a discount was considered for the order, it should also be applied. */

        //HashMap<Good,Integer>goodsamount=this.goodsAmount;
        int sum=0;
        ArrayList<Good>shopgoods=this.customer.getShop().getgoods();
        for(int i=0;i<shopgoods.size();i++){
            if(this.goodsAmount.containsKey(shopgoods.get(i)))sum+=this.goodsAmount.get(shopgoods.get(i))*shopgoods.get(i).getPrice();
        }
        if(discount!=null)sum=(int)(sum-(sum*((float)this.discount.getPrecent()/100)));
        return sum;
    }

    public void adddiscount(Discount discount){
		applies the discount to the purchase. 
		This function is applied in such a way that the application fee is deducted from it as much as the discount percentage .
        if(this.discount==null)
        this.discount=discount;
    }

}
class Discount{
	# This class is for describing the discounts that are assigned to the customer.
	# Note: Each user can only use a discount once .
    private int id;
    private int precent;
    private Order order;
    private Customer customer;

    public Discount(int id,int precent,Customer customer){
		# The input of this builder is the discount ID and discount percentage and the customer it is issued for
        this.id=id;
        this.precent=precent;
        customer.getShop().addDisount(this);


    }

    public int getId() {
        return id;
    }

    public int getPrecent(){
        return precent;
    }

    public void setorder(Order order){
		# It attributes the discount to the order.
        this.order=order;
        order.adddiscount(this);
    }

}

public class Ecommerce{

    public static void main(String[] args) {
        Shop shop=new Shop("shop");
        Customer customer=new Customer(0,"customer",shop);
        String command=new String();
        Scanner scanner=new Scanner(System.in);
        int i=0;
        //System.out.println("passed initiation");
        while(i==0){
            //System.out.println("enter your command");
            command=scanner.nextLine();


            switch (command){
                case "add":
                    //System.out.println("add command selected,which one do you add");
                    String commandAdd;
                    commandAdd=scanner.nextLine();
                    switch (commandAdd){
                        case"customer":
                            int customerId;
                            String customerName;
                            customerId=scanner.nextInt();
                            scanner.nextLine();
                            customerName=scanner.nextLine();
                            customer=new Customer(customerId,customerName,shop);
                            //System.out.println("customer added" );
                            break;
                        case"good":
                            int goodId;
                            String goodName;
                            int goodPrice;
                            int goodamount;
                            goodId=scanner.nextInt();
                            scanner.nextLine();
                            goodName=scanner.nextLine();
                            goodPrice=scanner.nextInt();
                            scanner.nextLine();
                            goodamount=scanner.nextInt();
                            scanner.nextLine();
                            Good good=new Good(goodName,goodId,goodPrice,shop);
                            shop.incrementGood(good,goodamount);
                            //System.out.println("good added" );
                            break;
                        case "repository":
                            int respiratoryid;
                            int respiratroyCapacity;
                            respiratoryid=scanner.nextInt();
                            scanner.nextLine();
                            respiratroyCapacity=scanner.nextInt();
                            scanner.nextLine();
                            Respiratory respiratory=new Respiratory(respiratoryid,respiratroyCapacity,shop);
                            //System.out.println("respiratory added" );
                            break;
                        case"order":
                            int orderId;
                            int orderCustomerId;
                            orderId=scanner.nextInt();
                            scanner.nextLine();
                            orderCustomerId=scanner.nextInt();
                            scanner.nextLine();
                            Customer customerOrder=shop.getCustomer(orderCustomerId);
                            //System.out.println("the balance of customer is"+customerOrder.getBalance());
                            Order order=new Order(orderId,customer,customerOrder);
                            order.setStatus("pending");
                            //System.out.println("order added" );
                            break;
                        case "balance":
                            int balanceCustomerId;
                            int balanceAdd;
                            balanceCustomerId=scanner.nextInt();
                            scanner.nextLine();
                            balanceAdd=scanner.nextInt();
                            scanner.nextLine();
                            Customer customerBalance=shop.getCustomer(balanceCustomerId);
                            //System.out.println("the balance of customer is"+customerBalance.getBalance());
                            customerBalance.setBalance(customerBalance.getBalance()+balanceAdd);
                            //System.out.println("the balance of customer is"+customerBalance.getBalance());
                            //System.out.println("balance added" );
                            break;
                        case "item":
                            int itemOrderId;
                            int itemGoodId;
                            int itemGoodAmount;
                            itemOrderId=scanner.nextInt();
                            scanner.nextLine();
                            itemGoodId=scanner.nextInt();
                            scanner.nextLine();
                            itemGoodAmount=scanner.nextInt();
                            scanner.nextLine();
                            Good itemGood=shop.getgood(itemGoodId);
                            Order orderItem=customer.getorder(itemOrderId);
                            orderItem.additem(itemGood,itemGoodAmount);
                            //System.out.println("item added" );
                            break;
                        case "discount":
                            int discountId;
                            int discountPrecent;
                            discountId=scanner.nextInt();
                            scanner.nextLine();
                            discountPrecent=scanner.nextInt();
                            scanner.nextLine();
                            Discount discount=new Discount(discountId,discountPrecent,customer);
                            //System.out.println("discount added" );
                            break;
                    }
                    break;
                case "report":
                    //System.out.println("report command selected,which one do you want to report" );
                    String reportCommand;
                    reportCommand=scanner.nextLine();
                    switch (reportCommand){
                        case"customers":
                            for(int j=1;j<shop.customerList().size();j++){
                                System.out.print(shop.customerList().get(j).getId()+",");
                                System.out.print(shop.customerList().get(j).getName()+",");
                                System.out.print(shop.customerList().get(j).getBalance()+",");
                                System.out.print(shop.customerList().get(j).getOrders().size()+",");
                                System.out.print(shop.customerList().get(j).getSubmitedorders().size());
                                System.out.println();
                            }
                            //System.out.println("customer reported" );
                            break;
                        case "repositories":
                            for(int j=0;j<shop.respiratoryList().size();j++){
                                System.out.print(shop.respiratoryList().get(j).getId()+",");
                                System.out.print(shop.respiratoryList().get(j).getfreecapacity()+",");
                                System.out.print(shop.respiratoryList().get(j).getCapacity());

                                System.out.println();
                            }
                            //System.out.println("respiratories reported" );
                            break;
                        case "income":
                            System.out.println(shop.getIncome());
                            //System.out.println("income reported" );
                            break;

                    }
                    break;
                case"submit":
                    //System.out.println("submit selected,which one do you want to submit" );
                    String submitCommand;
                    submitCommand=scanner.nextLine();
                    switch (submitCommand) {
                        case "order":
                            int orderIdSubmit = scanner.nextInt();
                            scanner.nextLine();
                            Order SubmitOrder = customer.getorder(orderIdSubmit);
                            Customer orderCustomer = SubmitOrder.getCustomer();
                            orderCustomer.submitorder(SubmitOrder);
                            //System.out.println("order submited" );
                            break;
                        case"discount":
                            int OrderidDiscount=scanner.nextInt();
                            scanner.nextLine();
                            int discountIdDiscount=scanner.nextInt();
                            scanner.nextLine();
                            Order orderDiscount=customer.getorder(OrderidDiscount);
                            Discount discountDiscount=shop.getdiscount(discountIdDiscount);
                            discountDiscount.setorder(orderDiscount);
                            //System.out.println("discount submited" );
                            break;
                    }
                    break;
                case"terminate":
                    i = 1;
                    //System.out.println("goodbye" );
                    break;
                case"remove":
                    //System.out.println("remove selected,which one do you want to submit" );
                    String removeCommand=scanner.nextLine();
                    switch (removeCommand){
                        case"item":
                            int orderIdRemove=scanner.nextInt();
                            //System.out.println(orderIdRemove);
                            scanner.nextLine();
                            int orderItemIdRemove=scanner.nextInt();
                            //System.out.println(orderItemIdRemove);
                            scanner.nextLine();
                            Order orderRemove=customer.getorder(orderIdRemove);
                            //System.out.println("the id of order is"+orderRemove.getId());
                            Good orderItemRemove=shop.getgood(orderItemIdRemove);
                            //System.out.println(orderItemRemove.getName());
                            orderRemove.removeitem(orderItemRemove);
                            break;
                    }
                    break;

            }


        }

    }
}

