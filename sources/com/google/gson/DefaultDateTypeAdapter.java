package com.google.gson;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

final class DefaultDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(String str) {
        this((DateFormat) new SimpleDateFormat(str, Locale.US), (DateFormat) new SimpleDateFormat(str));
    }

    DefaultDateTypeAdapter(int i) {
        this(DateFormat.getDateInstance(i, Locale.US), DateFormat.getDateInstance(i));
    }

    public DefaultDateTypeAdapter(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    DefaultDateTypeAdapter(DateFormat dateFormat, DateFormat dateFormat2) {
        this.enUsFormat = dateFormat;
        this.localFormat = dateFormat2;
    }

    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonPrimitive jsonPrimitive;
        synchronized (this.localFormat) {
            jsonPrimitive = new JsonPrimitive(this.enUsFormat.format(date));
        }
        return jsonPrimitive;
    }

    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement instanceof JsonPrimitive) {
            Date deserializeToDate = deserializeToDate(jsonElement);
            if (type == Date.class) {
                return deserializeToDate;
            }
            if (type == Timestamp.class) {
                return new Timestamp(deserializeToDate.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(deserializeToDate.getTime());
            }
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
        throw new JsonParseException("The date should be a string value");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:8|9|10|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3 = com.google.gson.internal.bind.util.ISO8601Utils.parse(r4.getAsString(), new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        throw new com.google.gson.JsonSyntaxException(r4.getAsString(), r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r3 = r3.enUsFormat.parse(r4.getAsString());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date deserializeToDate(com.google.gson.JsonElement r4) {
        /*
            r3 = this;
            java.text.DateFormat r0 = r3.localFormat
            monitor-enter(r0)
            java.text.DateFormat r1 = r3.localFormat     // Catch:{ ParseException -> 0x0011 }
            java.lang.String r2 = r4.getAsString()     // Catch:{ ParseException -> 0x0011 }
            java.util.Date r3 = r1.parse(r2)     // Catch:{ ParseException -> 0x0011 }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r3
        L_0x000f:
            r3 = move-exception
            goto L_0x0038
        L_0x0011:
            java.text.DateFormat r3 = r3.enUsFormat     // Catch:{ ParseException -> 0x001d }
            java.lang.String r1 = r4.getAsString()     // Catch:{ ParseException -> 0x001d }
            java.util.Date r3 = r3.parse(r1)     // Catch:{ ParseException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r3
        L_0x001d:
            java.lang.String r3 = r4.getAsString()     // Catch:{ ParseException -> 0x002d }
            java.text.ParsePosition r1 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x002d }
            r2 = 0
            r1.<init>(r2)     // Catch:{ ParseException -> 0x002d }
            java.util.Date r3 = com.google.gson.internal.bind.util.ISO8601Utils.parse(r3, r1)     // Catch:{ ParseException -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r3
        L_0x002d:
            r3 = move-exception
            com.google.gson.JsonSyntaxException r1 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x000f }
            java.lang.String r4 = r4.getAsString()     // Catch:{ all -> 0x000f }
            r1.<init>(r4, r3)     // Catch:{ all -> 0x000f }
            throw r1     // Catch:{ all -> 0x000f }
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.DefaultDateTypeAdapter.deserializeToDate(com.google.gson.JsonElement):java.util.Date");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DefaultDateTypeAdapter.class.getSimpleName());
        sb.append('(').append(this.localFormat.getClass().getSimpleName()).append(')');
        return sb.toString();
    }
}
