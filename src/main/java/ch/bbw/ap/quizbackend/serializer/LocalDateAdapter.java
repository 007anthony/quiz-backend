package ch.bbw.ap.quizbackend.serializer;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        if(localDate != null) {
            jsonWriter.value(localDate.format(DateTimeFormatter.ISO_DATE));
        }
        else {
            jsonWriter.nullValue();
        }

    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {

        LocalDate localDate = null;
        if(jsonReader.peek() != JsonToken.NULL) {
                localDate = LocalDate.parse(jsonReader.nextString());

        }
        else {
            jsonReader.nextNull();
        }

        return localDate;
    }
}
