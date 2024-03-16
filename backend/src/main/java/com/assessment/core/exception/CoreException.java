package com.assessment.core.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CoreException extends RuntimeException {

    CoreError coreError;

    public CoreException(CoreError coreError) {
        this.coreError = coreError;
    }

}
