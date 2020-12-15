package org.example.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;
import org.example.jackson.Result;

import java.io.IOException;

public class ResultSerializer extends StdSerializer<Result> {

    /**
     * https://www.runoob.com/java/java-modifier-types.html
     * 饰符	        当前类	同一包内	子孙类(同一包)	子孙类(不同包)	其他包
     * public	    Y	    Y	    Y	            Y	            Y
     * protected	Y	    Y	    Y	            Y/N（说明）	    N
     * default	    Y	    Y	    Y	            N	            N
     * private	    Y	    N	    N	            N	            N
     * @param t
     */
    protected ResultSerializer(Class<Result> t) {
        super(t);
    }

    @Override
    public void serialize(Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("code", result.getCode());
        jsonGenerator.writeStringField("message", result.getMessage());

        if (StringUtils.isNotEmpty(result.getErrors()))
        {
            jsonGenerator.writeStringField("errors", result.getErrors());
        }

        jsonGenerator.writeEndObject();

    }
}
