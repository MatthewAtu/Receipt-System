abstract class Payment {
    private double amount;
    protected String type;
    protected String name;

    public Payment(double amount){
        this.amount = amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public double getAmount(){
        return this.amount;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }

}
