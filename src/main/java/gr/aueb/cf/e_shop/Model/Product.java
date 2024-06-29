package gr.aueb.cf.e_shop.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name" , nullable = false)
    private String productName;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock_quantity",nullable = false)
    private Integer stockQuantity;

    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Getter(AccessLevel.PROTECTED)
    private List<OrderItem> orderItems;*/

    /*public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setProduct(this);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
        orderItem.setProduct(null);
    }*/


}