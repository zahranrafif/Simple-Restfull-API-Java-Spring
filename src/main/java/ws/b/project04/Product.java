
package ws.b.project04;

/**
 *
 * @author Zahran Rafif Pc
 */
public class Product {
    //variabel
    private String id;
    private String name;
    private Integer price;
    private Integer number;
    private Integer total;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getPrice(){
        return price;
    }
    public void setPrice(Integer price){
        this.price = price;
    }
    public Integer getNumber(){
        return number;
    }
    public void setNumber(Integer number){
        this.number = number;
    }
    public Integer getTotal(){
        return total;
    }
    public void setTotal(){
        this.total = price * number;
    }
}
