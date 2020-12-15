package org.example.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Integer code;
    private String message;
    private String errors;

    @JsonProperty("propNameByJsonProperty")
    private Long propNameByJsonProperty;

    @JsonIgnore
    private Map propNameByJsonIgnore;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private HashMap propNameByJsonIgnorePropertiesIgnoreUnknown;

//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
}
