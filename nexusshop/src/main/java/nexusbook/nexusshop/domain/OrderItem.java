package nexusbook.nexusshop.domain;

import lombok.Getter;
import lombok.Setter;
import nexusbook.nexusshop.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count; //주문 수량

    //== 생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        System.out.println("주문에서 보낸 값(11이어야함) =" + count);
        item.removeStock(count);
        System.out.println(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    /** 주문 취소 **/
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
