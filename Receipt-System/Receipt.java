import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Receipt { 
    private Store s;
    private Customer c;
    private ArrayList<Item> boughtitems = new ArrayList<>();
    private Payment payment;
    protected final double TAX = 0.13;

    DecimalFormat formatter = new DecimalFormat("$#,##0.00");

    //empty constructor
    public Receipt(){

    }

    public Receipt(Store s, Customer c, Item items, Payment payment){
        this.s = s;
        this.c = c;
        this.boughtitems.add(items);
        this.payment = payment;
    }
    
    public void setStore(Store StoreN){
        this.s = StoreN;
        //System.out.println(this.s.getName());
    }
    public Store getStore(){
        return this.s; 
    }

    public void setCustomer(Customer CustomerN){
        this.c = CustomerN;
    }
    public Customer getCustomer(){
        return this.c; 
    }

    public void addItem(Item ItemN){
        boughtitems.add(ItemN);
    }
    public ArrayList<Item> getItems(){
        return this.boughtitems;
    }
    public void clearItems(){
        for (Item i: boughtitems){
            i = null;
        }
    }
    public Payment getpayment(){
        return this.payment;
    }
    public void setPayment(Payment payment){
        this.payment = payment;
    }
    public double total(){
        double finalprice;
        double price = 0;
            for (Item i: boughtitems){
                double total = (i.getPrice() * i.GetQuantity());
                double taxamt = (TAX * total);
                finalprice = total + taxamt;
                price += finalprice;
            }
        double p = Math.round(price*100)/100;
        return p;
    }
    public void calculateTotal(){
    double finalprice;
    double price = 0;
        for (Item i: boughtitems){
            double total = (i.getPrice() * i.GetQuantity());
            double taxamt = (TAX * total);
            finalprice = total + taxamt;
            price += finalprice;
            BigDecimal amt1 = new BigDecimal(finalprice);
            String Str = formatter.format(amt1);
            System.out.println("Total of " + i.getItemName() +  ": " +  Str);
        }
        BigDecimal amt2 = new BigDecimal(price);
        String currStr = formatter.format(amt2);
        if (this.payment.getName() == null){//check the payment methods
           System.out.println("Payment method: " + this.payment.getType()); 
        }if (this.payment.getType() == null){
            System.out.println("Payment method: " + this.payment.getName()); 
        }
        System.out.println("Total: " +  currStr);
        System.out.println("****************************");
    }
}
