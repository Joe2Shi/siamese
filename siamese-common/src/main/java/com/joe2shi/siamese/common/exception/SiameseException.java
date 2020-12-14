package com.joe2shi.siamese.common.exception;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SiameseException extends RuntimeException {

    private ResponseEnum responseEnum;

}
