package com.microservices.ProductService.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="price")
    private long price;
    @Column(name="quantity")
    private long quantity;
}
