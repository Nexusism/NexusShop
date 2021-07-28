package nexusbook.nexusshop.domain.item;

import lombok.Getter;
import lombok.Setter;
import nexusbook.nexusshop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        System.out.println("removeStock이 실행되는지?");
        int restStock = this.stockQuantity - quantity;
        System.out.println(restStock);
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");   //왜 예외발생을 안하지? OrderService Test에서
        }
        this.stockQuantity = restStock;
    }
}
