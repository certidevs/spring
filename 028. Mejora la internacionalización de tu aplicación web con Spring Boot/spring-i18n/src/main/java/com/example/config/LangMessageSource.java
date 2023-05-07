package com.example.config;

import com.example.model.Language;
import com.example.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component("messageSource")
public class LangMessageSource extends AbstractMessageSource {

    private static final String DEFAULT_LOCALE = "en";

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        System.out.println(LangMessageSource.class.getName() + " resolveCode: " + key + " " + locale);
        Language lang = languageRepository.findByKeyAndLocale(key, locale.getLanguage());
        if (lang == null) {
            lang = languageRepository.findByKeyAndLocale(key, DEFAULT_LOCALE);
        }

        return new MessageFormat(lang.getValue(), locale);
    }
}
