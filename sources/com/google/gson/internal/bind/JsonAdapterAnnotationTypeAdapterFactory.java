package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter jsonAdapter = (JsonAdapter) typeToken.getRawType().getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v12, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX WARNING: type inference failed for: r6v5, types: [com.google.gson.TypeAdapter<?>, com.google.gson.TypeAdapter] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r7, com.google.gson.Gson r8, com.google.gson.reflect.TypeToken<?> r9, com.google.gson.annotations.JsonAdapter r10) {
        /*
            r6 = this;
            java.lang.Class r6 = r10.value()
            com.google.gson.reflect.TypeToken r6 = com.google.gson.reflect.TypeToken.get(r6)
            com.google.gson.internal.ObjectConstructor r6 = r7.get(r6)
            java.lang.Object r6 = r6.construct()
            boolean r7 = r6 instanceof com.google.gson.TypeAdapter
            if (r7 == 0) goto L_0x0017
            com.google.gson.TypeAdapter r6 = (com.google.gson.TypeAdapter) r6
            goto L_0x004d
        L_0x0017:
            boolean r7 = r6 instanceof com.google.gson.TypeAdapterFactory
            if (r7 == 0) goto L_0x0022
            com.google.gson.TypeAdapterFactory r6 = (com.google.gson.TypeAdapterFactory) r6
            com.google.gson.TypeAdapter r6 = r6.create(r8, r9)
            goto L_0x004d
        L_0x0022:
            boolean r7 = r6 instanceof com.google.gson.JsonSerializer
            if (r7 != 0) goto L_0x0033
            boolean r10 = r6 instanceof com.google.gson.JsonDeserializer
            if (r10 == 0) goto L_0x002b
            goto L_0x0033
        L_0x002b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference."
            r6.<init>(r7)
            throw r6
        L_0x0033:
            r10 = 0
            if (r7 == 0) goto L_0x003b
            r7 = r6
            com.google.gson.JsonSerializer r7 = (com.google.gson.JsonSerializer) r7
            r1 = r7
            goto L_0x003c
        L_0x003b:
            r1 = r10
        L_0x003c:
            boolean r7 = r6 instanceof com.google.gson.JsonDeserializer
            if (r7 == 0) goto L_0x0043
            r10 = r6
            com.google.gson.JsonDeserializer r10 = (com.google.gson.JsonDeserializer) r10
        L_0x0043:
            r2 = r10
            com.google.gson.internal.bind.TreeTypeAdapter r6 = new com.google.gson.internal.bind.TreeTypeAdapter
            r5 = 0
            r0 = r6
            r3 = r8
            r4 = r9
            r0.<init>(r1, r2, r3, r4, r5)
        L_0x004d:
            if (r6 == 0) goto L_0x0053
            com.google.gson.TypeAdapter r6 = r6.nullSafe()
        L_0x0053:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter):com.google.gson.TypeAdapter");
    }
}
