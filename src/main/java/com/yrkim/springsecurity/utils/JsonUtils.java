package com.yrkim.springsecurity.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper mapper() {
        return mapper;
    }

    public static String mapToJsonString(@NotNull Map<String, String> map) throws Exception {
        String result = Optional.of(mapper.writeValueAsString(map))
                .orElseThrow(Exception::new);
        return result;
    }

    public static String mapToJsonPrettyString(@NotNull Map<String, String> map) throws Exception {
        String result = Optional.of(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map))
                .orElseThrow(Exception::new);
        return result;
    }

    public static String listToJsonString(@NotNull List<?> list) throws Exception {
        String result = Optional.of(mapper.writeValueAsString(list))
                .orElseThrow(Exception::new);
        return result;
    }

    public static String listToJsonPrettyString(@NotNull List<?> list) throws Exception {
        String result = Optional.of(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list))
                .orElseThrow(Exception::new);
        return result;
    }

    public static String objToJsonString(@NotNull Object obj) throws Exception {
        String result = Optional.of(mapper.writeValueAsString(obj))
                .orElseThrow(Exception::new);
        return result;
    }

    public static String objToJsonPrettyString(@NotNull Object obj) throws Exception {
        String result = Optional.of(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj))
                .orElseThrow(Exception::new);
        return result;
    }
}
