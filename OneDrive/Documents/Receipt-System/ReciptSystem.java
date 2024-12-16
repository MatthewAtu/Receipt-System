

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ReciptSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Create a list to store receipts
            ArrayList<Receipt> receipts = new ArrayList<>();

            // Create a list to store customers
            ArrayList<Customer> customers = new ArrayList<>();

            // Create a list to store stores
            ArrayList<Store> stores = new ArrayList<>();

            DecimalFormat formatter = new DecimalFormat("$#,##0.00");//money format object

            // Main loop to interact with the user
            while (true) {
                System.out.println("1. Add Receipt");
                System.out.println("2. View Receipts");
                System.out.println("3. Generate Reports");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        // Add a receipt
                        Receipt receipt = new Receipt();
                        System.out.println("Enter store name: ");
                        String storeName = scanner.next();
                        
                        //check if the store is already exists before add it
                        Store store = findStoreByName(stores, storeName);
                        if (store == null) {
                            store = new Store(storeName);
                            stores.add(store);
                        }
                        receipt.setStore(store);

                        System.out.println("Enter customer name: ");
                        String customerName = scanner.next();

                        //check if the customer is already exists before add it
                        Customer customer = findCustomerByName(customers, customerName);
                        if (customer == null) {
                            customer = new Customer(customerName);
                            customers.add(customer);
                        }
                        receipt.setCustomer(customer);
                        // Add items to the receipt
                        outerloop:{
                            while (true) {
                                System.out.println("Enter item name (or 'done' to finish): ");
                                String itemName = scanner.next();
                                if (itemName.equals("done")) {
                                    break;
                                }
                                System.out.println("Enter item price: ");
                                double price = 0;
                                int quantity = 0;
                                try {
                                    price = scanner.nextDouble();//try to receive the input
                                } catch (Exception e) {//if exception is found br
                                    System.out.println("invalid input.");
                                    receipt.setCustomer(null);
                                    receipt.setStore(null);
                                    scanner.nextLine();
                                    break outerloop;
                                }
                                try{
                                    System.out.println("Enter item quantity: ");
                                    quantity = scanner.nextInt();
                                }catch(Exception e){
                                    System.out.println("invalid input.");
                                    receipt.setCustomer(null);
                                    receipt.setStore(null);
                                    scanner.nextLine();
                                    break outerloop;
                                }
                                Item item = new Item(itemName, price, quantity);
                                receipt.addItem(item);
                            }
                        
                            System.out.println("How was you like to pay?: ");
                            System.out.println("1. cash");
                            System.out.println("2. card");
                            System.out.print("Enter your choice: ");
                            int paymenttype = scanner.nextInt();
                            switch (paymenttype) {
                                case 1 -> {
                                    double total = receipt.total();
                                    Payment cash = new Cash(total, "Cash");
                                    receipt.setPayment(cash); //ask for payment type and make an object
                                }
                                case 2 -> {
                                    double total = receipt.total();
                                    System.out.println("what type: ");
                                    System.out.println("1. credit");
                                    System.out.println("2. debit");
                                    int cardtype = 0;
                                    try{
                                        System.out.print("Enter your choice: ");
                                        cardtype = scanner.nextInt();
                                    }catch(Exception e){
                                        System.out.println("invalid input.");
                                        receipt.setCustomer(null);
                                        receipt.setStore(null);
                                        receipt.clearItems();
                                        scanner.nextLine();
                                        break outerloop;
                                    }
                                    switch (cardtype) {
                                        case 1 -> {
                                            Payment card = new Card(total, "credit");
                                            receipt.setPayment(card);
                                        }
                                        case 2 -> {
                                            Payment card = new Card(total, "debit"); 
                                            receipt.setPayment(card);
                                        }
                                        default -> {
                                            System.out.println("Invalid input");
                                            break outerloop;
                                        }
                                    }
                                }
                                default -> {
                                    System.out.println("invalid input");
                                    break outerloop;
                                }
                            }

                        
                        // Calculate total and add receipt to lists
                        receipt.calculateTotal();
                        receipts.add(receipt);
                        store.addReceipt(receipt);
                        customer.addReceipt(receipt);
                        System.out.println("stored successfully...");
                        }     
                    }
                    case 2 -> {
                        // View receipts
                        System.out.println("View receipts by:");
                        System.out.println("1. Customer");
                        System.out.println("2. Store");
                        System.out.print("Enter your choice: ");
                        int viewChoice = 0;
                        
                        try {
                            viewChoice = scanner.nextInt();//try to receive the input
                        } catch (Exception e) {//if exception is found br
                            System.out.println("invalid input.");
                            scanner.nextLine();
                            break;
                        }
                    switch (viewChoice) {
                        case 1 -> {
                            System.out.print("Enter customer name: ");
                            String customerNameToView = scanner.next();
                            Customer customerToView = findCustomerByName(customers, customerNameToView);
                            if (customerToView != null) {
                                customerToView.viewReceipts();
                            } else {
                                System.out.println("Customer not found.");
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter store name: ");
                            String storeNameToView = scanner.next();
                            Store storeToView = findStoreByName(stores, storeNameToView);
                            if (storeToView != null) {
                                storeToView.viewReceipts();//not finished
                            } else {
                                System.out.println("Store not found.");
                            }
                        }
                        default -> {
                            System.out.println("Invalid input.");
                        }
                    }
                    }
                    case 3 -> {
                    // Generate reports
                    // Implement report generation methods here
                    int counter = 0;
                    System.out.println("Generate Report based on: ");
                    System.out.println("1. Customer");
                    System.out.println("2. Store");
                    System.out.println("3. Item");
                    System.out.println("Enter your choice: ");
                    int Uinput = 0;

                    try {
                        Uinput = scanner.nextInt();//try to receive the input
                    } catch (Exception e) {//if exception is found br
                        System.out.println("invalid input.");
                        scanner.nextLine();
                        break;
                    }
                    outerloop:{
                    switch (Uinput) {
                        case 1 -> {//what are all the things this customer bought and the stores he bought them at
                            System.out.println("Enter Customer name: ");
                            String CusName = scanner.next();
                            for (Receipt i: receipts){
                                if (CusName.equals(i.getCustomer().getName())){
                                    i.getCustomer().GenerateReports();
                                    break;  
                                }else if(++counter == receipts.size()) {
                                    System.out.println("no name matches");//might not work as intended 
                                    break;
                                }
                            }
                        }
                        case 2 -> {//what was bought and who bought it 
                            System.out.println("Enter Store name: ");
                            String CusName = scanner.next();
                            for (Receipt i: receipts){
                                if (CusName.equals(i.getStore().getName())){
                                    i.getStore().GenerateReports();
                                    break;
                                }else if(++counter == receipts.size()) {
                                    System.out.println("no name matches");//might not work as intended 
                                    break;
                                }
                            }
                        }
                        case 3 -> {
                            //for items get the name and check the items array in Receipt in order to see where the item was bought and how many for how much
                            System.out.println("Enter item name: ");
                            String itemName = scanner.next();
                            int totalprice = 0;
                            for (Receipt i: receipts){
                                for (Item j: i.getItems()){
                                    if (itemName.equals(j.getItemName())){
                                        totalprice += j.getPrice() * j.GetQuantity();
                                    }else if(++counter == receipts.size()) {
                                        System.out.println("no name matches");//might not work as intended 
                                        break;
                                    }
                                }
                            }
                            BigDecimal amt2 = new BigDecimal(totalprice);
                            String currStr = formatter.format(amt2);
                            System.out.println("total spent on:" + itemName + " "+ currStr);
                            System.out.println("****************************");
                        }
                        default -> {
                            System.out.println("Incorrect input.");
                        }
                    }
                    } //
                }  
                case 4 -> {
                        // Exit
                        System.out.println("Exiting...");
                        System.exit(0);
                }
                default -> System.out.println("Incorrect input.");
                }
                
            }
        }
    } 
     // Helper methods
private static Customer findCustomerByName(ArrayList<Customer> customers, String name) {
        // Implement the customrt search method
        for (Customer i: customers){
            if (name.equals(i.getName())){
                return i;
            }
        }
    return null;
}

private static Store findStoreByName(ArrayList<Store> stores, String name) {
        // Implement the store search method
        for (Store i: stores){
            if (name.equals(i.getName())){
                return i;
            }
        }
    return null;
}

}
