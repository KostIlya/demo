package ru.t1.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.demo.model.enums.AccountEnum;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class AccountRequestDTO {
    @JsonProperty("client_id")
    private UUID clientId;
    @JsonProperty("type_account")
    private AccountEnum type;
    @JsonProperty("balance")
    private BigDecimal balance;
}