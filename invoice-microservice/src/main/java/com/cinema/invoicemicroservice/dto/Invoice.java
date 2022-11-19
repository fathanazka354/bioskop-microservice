package com.cinema.invoicemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private Long customerId;
    private String seatCode;
    private LocalDate dateShowtime;
    private String movieName;
    private String firstName;
    private Double cash;
}
