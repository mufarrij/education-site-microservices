package com.simplesolutions.courseservice.convertor;

import com.simplesolutions.courseservice.model.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Convertor for String to Status enum
 *
 * @author Mufarrij
 * @since 22/7/2023
 */
@Component
public class StringToStatusConvertor implements Converter<String, Status> {

    @Override
    public Status convert(final String source) {
        return Arrays.stream(Status.values()).
                filter(v -> v.toString().toUpperCase().equals(source.toUpperCase())).findFirst().orElse(null);
    }
}
