import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer extends Receipt{
    private String CustomerName;
    private ArrayList<Receipt> storedrec2 = new ArrayList<>();


    //constructor for customer
    public Customer(String name){
        this.CustomerName = name;
    }
    public void setCustomer(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public void addReceipt(Receipt rec){
        storedrec2.add(rec);
    }
    public String getName(){
        return CustomerName;
    }
    
    public Receipt findReceiptforName(String Cname){
        for (Receipt i: storedrec2){
            if (i.getCustomer().getName().equals(Cname)){
                return i;
            }
        }
        return null;
    }

    public void viewReceipts(){//get money format for this
        //prints all reciepts of a specific customer
        System.out.println("************");
        System.out.println("customer name: " + this.CustomerName);
        for (Receipt i: storedrec2){
            if (i.getCustomer().getName().equals(this.CustomerName)){
                System.out.println("store: " + i.getStore().getName());
                //get items array and loop through it
                System.out.println("Items: ");
                for (Item j: i.getItems()){
                    BigDecimal amt1 = new BigDecimal(j.getPrice());
                    String Str = formatter.format(amt1);
                    System.out.println(j.getItemName());
                    System.out.println("price: " + Str);
                    System.out.println("quantity: " + j.GetQuantity());
                    //get money object and give total price of all the items
                    System.out.println("***************");
                    }
                }
            }     
    }
    public void GenerateReports(){//summarize spending of a specific customer
        System.out.println("Report on Customer: " + this.CustomerName);
        double reportPrice = 0;
        for (Receipt i: storedrec2){
            if (i.getCustomer().getName().equals(this.CustomerName)){
                for (Item j : i.getItems()){
                    reportPrice += (j.GetQuantity()*j.getPrice());
                }
            }
        }
        reportPrice = reportPrice + (reportPrice * TAX);
        BigDecimal amt1 = new BigDecimal(reportPrice);
        String Str = formatter.format(amt1); 
        System.out.println("Total spent: " + Str);//format the price
        System.out.println("****************************");
        }
}
    

