import java.util.*;

/* In this program, we implement a food vending machine program. Each of these devices must have the following fields:

     id (device number which is a positive integer) - int
     saleCount (Number of products sold by this device) - int
     saleAmount (the amount the device sold for) - int
     products (HashMap of products and their number) - HashMap<Product, Integer>
 
Each product must also have the following fields:

	(productID) - int
	name (product name) - String
	price (product price) - int

The way to give input to the program is as follows: in the first line, the number of distinct products that are going to be sold in the devices is given (m). 
Then, in the next lines, we enter the product specifications in the following format:

Product ID
Product Name
price product

After the product specifications are entered, the number of food vending machines (n) comes in the next line; 
In the next n lines, the product information of devices with id (device number) from one to n comes in order. 
(The first line corresponds to device 1 and ... and the nth line corresponds to the nth device.) 
In each line, a number (p) comes first, which indicates the total number of products that follow the line. (including duplicates); 
Then, in the continuation of the line, the identifier p of the product comes, which must be added to the corresponding device. 
(When the identifier of a product appears several times, it means that its inventory must be added, and similar identifiers are not necessarily consecutive).

After completing the entry of product identifiers for all devices, in the next line, the number of purchases (b) will appear, and in the next line b, 
in each line, the number of the device and then the product ID to be purchased will be mentioned.

Finally, in n lines (the number of food vending machines) in each line, first the number of the machine is printed, 
and after the character ":", first the saleCount of that machine, then the character ",", 
and finally the saleAmount value of that machine is printed. .

Note: The numbers of the devices are in the order of product entry and start from 1. But product identifiers are only a positive number.

input example:
3
1 
hibye 
100 
2 
gumdrop 
200 
3 
apple juice 
10 
3 
5 1 2 3 1 1 
3 3 2 3 
2 3 1 
7 
2 2 
2 2 
1 3 
1 1
3 3 
3 3
1 1 
output example:
1:3,210
2:1,200
3:1,10  */

class Product{
    private int id;
    private String name;
    private int price;
    public Product(int pid,String pname,int pprice){
        this.id=pid;
        this.name=pname;
        this.price=pprice;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
class VendingMachine{

    private int id;

    private int salecount;

    private int saleamount;

     HashMap<Product,Integer> menu;

    public VendingMachine(){
        this.salecount=0;
        this.saleamount=0;
        this.id=0;
        this.menu=new HashMap<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSaleamount(int saleamount) {
        this.saleamount = saleamount;
    }

    public int getSaleamount() {
        return saleamount;
    }

    public void setSalecount(int salecount) {
        this.salecount = salecount;
    }

    public int getSalecount() {
        return salecount;
    }

    public boolean buy (Product product) {
        if(menu.get(product)<=0)return false;
        this.menu.put(product,this.menu.get(product)-1);
        this.setSaleamount(this.getSaleamount()+product.getPrice());
        setSalecount(this.getSalecount()+1);
        //this.menu.put(product,this.menu.get(product)+1);
        return true;
    }

    public void addProduct(Product p){
       if( this.menu.containsKey(p)==true) {
           this.menu.put(p, this.menu.get(p)+1);
       }
       else this.menu.put(p,1);
    }


}
public class Main{
    public static void main(String[] args) {
        int product_number;
        int vendingmachine_number;
        int product_id;
        int buy_id;
        int vendingmachine_id;
        int product_key;
        String product_name;
        int product_price;
        int buy_number;
        int product_list_number;
        Integer one=new Integer(1);
        HashMap<Integer,Product> products=new HashMap<>();
        HashMap<Integer,VendingMachine>vendingmachines=new HashMap<>();
        Scanner scanner=new Scanner(System.in);
        product_number=scanner.nextInt();

        scanner.nextLine();
        for(int i=1;i<=product_number;i++){
            product_id=scanner.nextInt();
            scanner.nextLine();
            product_name=scanner.nextLine();
            product_price=scanner.nextInt();
            scanner.nextLine();
            Integer pid=new Integer(product_id);
            Product product=new Product(product_id,product_name,product_price);
            products.put(pid,product);
        }
        //System.out.println(products.get(1).getName());
        vendingmachine_number=scanner.nextInt();

        scanner.nextLine();

        for(int i=1;i<=vendingmachine_number;i++){
            product_list_number=scanner.nextInt();
            VendingMachine vendingMachine=new VendingMachine();
            vendingMachine.setId(i);
            vendingmachines.put(i,vendingMachine);
            for(int j=1;j<=product_list_number;j++){
                product_key=scanner.nextInt();
                Product getproduct=new Product(0,"string",0);
                getproduct=products.get(product_key);
                //System.out.println("passed");
                vendingmachines.get(i).addProduct(getproduct);
            }
            scanner.nextLine();
        }

        buy_number=scanner.nextInt();

        scanner.nextLine();

        for(int i=1;i<=buy_number;i++){
            vendingmachine_id=scanner.nextInt();
            product_id=scanner.nextInt();
            vendingmachines.get(vendingmachine_id).buy(products.get(product_id));
            scanner.nextLine();
        }
        for(int i=1;i<=vendingmachine_number;i++){
            System.out.println(i+":"+vendingmachines.get(i).getSalecount()+","+vendingmachines.get(i).getSaleamount());
        }



    }

}