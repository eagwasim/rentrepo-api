package com.noubug.rentrepo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskQueueModel<T> {
    T data;
}
