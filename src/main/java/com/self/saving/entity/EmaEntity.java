package com.self.saving.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ema")
public class EmaEntity {

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "ema50")
    private BigDecimal ema50;

    @Column(name = "ema100")
    private BigDecimal ema100;

    @Column(name = "ema200")
    private BigDecimal ema200;

    @CreationTimestamp
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
