package com.noubug.rentrepo.infrastructure.web.controllers.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ResponseJSON<T> {
    private T data;
    private String message;
}
