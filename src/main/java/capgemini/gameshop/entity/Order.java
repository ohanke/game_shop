package capgemini.gameshop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;
    private User user;
    private String date; //change later for LocalDate or smth like that
    private double totalValue;
    private OrderStatus orderStatus;

}
