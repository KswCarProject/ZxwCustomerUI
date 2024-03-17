package com.f2prateek.ln;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class Strings {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    Strings() {
    }

    static <T> String join(String str, Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        Iterator<T> it = collection.iterator();
        StringBuilder sb = new StringBuilder(toString((Object) it.next()));
        while (it.hasNext()) {
            T next = it.next();
            if (notEmpty(next)) {
                sb.append(str).append(toString((Object) next));
            }
        }
        return sb.toString();
    }

    static <T> String join(String str, T... tArr) {
        return join(str, Arrays.asList(tArr));
    }

    static String toString(InputStream inputStream) {
        StringWriter stringWriter = new StringWriter();
        copy(new InputStreamReader(inputStream), stringWriter);
        return stringWriter.toString();
    }

    static String toString(Reader reader) {
        StringWriter stringWriter = new StringWriter();
        copy(reader, stringWriter);
        return stringWriter.toString();
    }

    static int copy(Reader reader, Writer writer) {
        long copyLarge = copyLarge(reader, writer);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    static long copyLarge(Reader reader, Writer writer) {
        try {
            char[] cArr = new char[4096];
            long j = 0;
            while (true) {
                int read = reader.read(cArr);
                if (-1 == read) {
                    return j;
                }
                writer.write(cArr, 0, read);
                j += (long) read;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String toString(Object obj) {
        return toString(obj, "");
    }

    static String toString(Object obj, String str) {
        if (obj == null) {
            return str;
        }
        if (obj instanceof InputStream) {
            return toString((InputStream) obj);
        }
        if (obj instanceof Reader) {
            return toString((Reader) obj);
        }
        if (obj instanceof Object[]) {
            return join(", ", (T[]) (Object[]) obj);
        }
        return obj instanceof Collection ? join(", ", (Collection) obj) : obj.toString();
    }

    static boolean notEmpty(Object obj) {
        return toString(obj).trim().length() != 0;
    }
}
