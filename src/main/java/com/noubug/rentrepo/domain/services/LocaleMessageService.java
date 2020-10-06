package com.noubug.rentrepo.domain.services;

import java.util.List;

public interface LocaleMessageService {
    String getMessage(String key);

    String getMessage(String key, List<?> args);
}
