package Model;

public class Building extends BuildingDTO  {

   private String owner;
   
   public Building(String name, int price, int fee, int t_price) {
      super(name, price, fee, t_price);
   }

public String getOwner() {
	return owner;
}

public void setOwner(String owner) {
	this.owner = owner;
}
   
}
