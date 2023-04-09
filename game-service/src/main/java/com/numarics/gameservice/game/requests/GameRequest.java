package com.numarics.gameservice.game.requests;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

@Data
@ParameterObject
public class GameRequest {
    @Parameter
    private String name;

    @Parameter
    private String status;
}
