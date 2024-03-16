package com.assessment.core.dto.generic;

import com.assessment.core.exception.CoreException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorRes {
    private String status;
    private String message;

    public GenericErrorRes(CoreException e){
        this.status = "HTTP-"+e.getCoreError().getCode().value();
        this.message = e.getCoreError().getDescription();
    }

    public GenericErrorRes(int status, String message) {
        this.status =  "HTTP-"+ status;
        this.message = message;
    }
}
