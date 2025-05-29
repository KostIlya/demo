package ru.t1.demo.model;

import jakarta.persistence.*;
import ru.t1.demo.model.enums.AccountEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_account", nullable = false)
    private AccountEnum type;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
