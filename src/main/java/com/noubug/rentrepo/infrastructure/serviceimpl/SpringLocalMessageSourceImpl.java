package com.noubug.rentrepo.infrastructure.serviceimpl;

import com.noubug.rentrepo.domain.services.LocaleMessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SpringLocalMessageSourceImpl implements LocaleMessageService {
    private static final List<?> DEFAULT_ARGS = new ArrayList<>();

    private final MessageSource messageSource;

    @Inject
    public SpringLocalMessageSourceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key) {
        return getMessage(key, DEFAULT_ARGS);
    }

    @Override
    public String getMessage(String key, List<?> args) {
        return messageSource.getMessage(key, args.toArray(), LocaleContextHolder.getLocale());
    }
}
