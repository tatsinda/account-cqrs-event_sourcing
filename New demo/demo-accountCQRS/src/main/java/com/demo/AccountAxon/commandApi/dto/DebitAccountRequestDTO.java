package com.demo.AccountAxon.commandApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DebitAccountRequestDTO {

    private String aacountId;
    private String curreny;
    private double amount;
}
