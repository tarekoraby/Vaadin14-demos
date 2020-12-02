package org.vaadin.tarek.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.vaadin.flow.i18n.I18NProvider;

public class TranslationProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "test";


    @Override
    public List<Locale> getProvidedLocales() {
        //to read a bundle
        return Collections
                .unmodifiableList(Arrays.asList(new Locale("en"),
                        new Locale("fi"), new Locale("hi")));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);

        String value= bundle.getString(key);
        if (params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }
}
