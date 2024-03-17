package com.mapzen.helpers;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Objects;

public final class CharStreams {
    private static final int BUF_SIZE = 2048;

    public static String toString(Readable readable) throws IOException {
        return toStringBuilder(readable).toString();
    }

    private static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        copy(readable, sb);
        return sb;
    }

    public static long copy(Readable readable, Appendable appendable) throws IOException {
        checkNotNull(readable);
        checkNotNull(appendable);
        CharBuffer allocate = CharBuffer.allocate(2048);
        long j = 0;
        while (readable.read(allocate) != -1) {
            allocate.flip();
            appendable.append(allocate);
            j += (long) allocate.remaining();
            allocate.clear();
        }
        return j;
    }

    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }
}
