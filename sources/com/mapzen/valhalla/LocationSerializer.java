package com.mapzen.valhalla;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mapzen.valhalla.JSON;
import java.lang.reflect.Type;

class LocationSerializer implements JsonSerializer<JSON.Location> {
    LocationSerializer() {
    }

    public JsonElement serialize(JSON.Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = (JsonObject) new GsonBuilder().create().toJsonTree(location);
        if (location.heading < 0 || location.heading >= 360) {
            jsonObject.remove("heading");
        }
        return jsonObject;
    }
}
