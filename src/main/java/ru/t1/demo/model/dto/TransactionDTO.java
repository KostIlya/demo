package ru.t1.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class TransactionDTO {
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("sum_transaction")
    private Double sumTransaction;
    @JsonProperty("date_time")
    private LocalDateTime dateTime;
}
