package com.szchoiceway.customerui.kt_like;

public interface ObjectHelper {
    void runCatching(Function0Void function0Void) {
        try {
            function0Void.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    <T> T copy(T t) {
        return DataClassHelper.copy(t);
    }

    <T> T copy(T t, Class cls) {
        return DataClassHelper.copy(t, cls);
    }

    int hashCode(Object obj) {
        return DataClassHelper.hashCode(obj);
    }

    int hashCode(Object obj, Class cls) {
        return DataClassHelper.hashCode(obj, cls);
    }
}
