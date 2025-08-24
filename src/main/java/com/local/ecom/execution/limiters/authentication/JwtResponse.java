package com.local.ecom.execution.limiters.authentication;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record JwtResponse(@JsonProperty("token") String authToken,
                          @JsonProperty("expires_at") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss VV") ZonedDateTime expiresAt) {

}
