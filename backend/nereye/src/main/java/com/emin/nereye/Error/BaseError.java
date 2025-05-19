package com.emin.nereye.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BaseError {
    private int status;
    private  String message;
    private  long timeStamp;

}
