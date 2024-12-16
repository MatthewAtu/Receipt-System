import java.math.BigDecimal;
import java.util.ArrayList;

public class Store extends Receipt{
    private String Store;
    private ArrayList<Receipt> storedrec1 = new ArrayList<>();

    public Store(String StoreName){
        Store = StoreName;
    }
    //it doesnt make sense to have two of basically the same methods in two diffrent classes
    public void addReceipt(Receipt rec){
        storedrec1.add(rec);
    }
    public String getName(){
        return Store;
    }
    public void viewReceipts(){
        System.out.println("************");
        System.out.println("Store name: " + this.Store);
        for (Receipt i: storedrec1){
            if (i.getStore().getName().equals(this.Store)){
                System.out.println("Customer: " + i.getCustomer().getName());
                for (Item j: i.getItems()){
                    BigDecimal amt1 = new BigDecimal(j.getPrice());
                    String Str = formatter.format(amt1);
                    System.out.println(j.getItemName());
                    System.out.println("price: " + Str);
                    System.out.println("quantity: " + j.GetQuantity());
                    System.out.println("***************");
                }
            }
        }
}
public void GenerateReports(){//summarize spending of a specific customer
    System.out.println("Report on store: " + this.Store);
    double reportPrice = 0;
    for (Receipt i: storedrec1){
        if (i.getStore().getName().equals(this.Store)){
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
