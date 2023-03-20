package az.elvinali.ps.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Double amount;
    private String transactionId;
    private String paymentStatus;
}
