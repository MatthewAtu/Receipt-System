public class Card extends Payment{
    public Card(double amount, String type){
        super(amount);
        this.type = type;
    }
    @Override
    public String getType(){
        return this.type;
    }



}
