public class Cash extends Payment{
    public Cash(double amount, String name){
        super(amount);
        this.name = name;
    }
    @Override
    public String getName(){
        return this.name;
    }
    
}
