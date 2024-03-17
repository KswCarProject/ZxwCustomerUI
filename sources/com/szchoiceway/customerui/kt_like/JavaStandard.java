package com.szchoiceway.customerui.kt_like;

import com.szchoiceway.customerui.kt_like.JavaStandard;
import kotlin.jvm.functions.Function1;

public class JavaStandard {
    public static <T> T as(Object obj) {
        return obj;
    }

    public static boolean isInRange(double d, double d2, double d3) {
        return d >= d2 && d <= d3;
    }

    public static boolean isInRange(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    public static boolean isUntilRange(double d, double d2, double d3) {
        return d >= d2 && d < d3;
    }

    public static boolean isUntilRange(int i, int i2, int i3) {
        return i >= i2 && i < i3;
    }

    public static <T> T also(T t, Function1Void<T> function1Void) {
        function1Void.invoke(t);
        return t;
    }

    public static <T, R> R let(T t, Function1<T, R> function1) {
        return function1.invoke(t);
    }

    public static <T> Option<T> option(T t) {
        return (Option) also(new Option(), new Function1Void(t) {
            public final /* synthetic */ Object f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                Object unused = ((JavaStandard.Option) obj).variable = this.f$0;
            }
        });
    }

    public static <T> T runIf(T t, Function1<T, Boolean> function1, Function1Void<T> function1Void) {
        if (function1.invoke(t).booleanValue()) {
            function1Void.invoke(t);
        }
        return t;
    }

    public static <T> void runIfNonNull(T t, Function1Void<T> function1Void) {
        runIf(t, $$Lambda$JavaStandard$CNghoUODPcLNPbRDzbn5mnExck.INSTANCE, function1Void);
    }

    public static <T> boolean runIfNonNullCondition(T t, Function1<T, Boolean> function1) {
        if (t != null) {
            return function1.invoke(t).booleanValue();
        }
        return false;
    }

    public static void repeat(int i, Function1Void<Integer> function1Void) {
        for (int i2 = 0; i2 < i; i2++) {
            function1Void.invoke(Integer.valueOf(i2));
        }
    }

    public static int safeToNumber(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static String buildString(Function1Void<StringBuilder> function1Void) {
        return ((StringBuilder) also(new StringBuilder(), function1Void)).toString();
    }

    private JavaStandard() {
    }

    public static class Option<T> {
        private static final Object INVALID_VALUE = new Object();
        /* access modifiers changed from: private */
        public T variable;

        public Option<T> condition(Function1<T, Boolean> function1) {
            T t = this.variable;
            T t2 = INVALID_VALUE;
            if (t != t2 && !function1.invoke(t).booleanValue()) {
                this.variable = t2;
            }
            return this;
        }

        public Option<T> also(Function1Void<T> function1Void) {
            T t = this.variable;
            if (t != INVALID_VALUE) {
                function1Void.invoke(t);
            }
            return this;
        }

        public void run(Function1Void<T> function1Void) {
            T t = this.variable;
            if (t != INVALID_VALUE) {
                function1Void.invoke(t);
            }
        }

        public void runCatching(Function1Void<T> function1Void) {
            T t = this.variable;
            if (t != INVALID_VALUE) {
                try {
                    function1Void.invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Option<T> nonNull() {
            return condition($$Lambda$JavaStandard$Option$CNghoUODPcLNPbRDzbn5mnExck.INSTANCE);
        }

        public Option<T> isNull() {
            return condition($$Lambda$JavaStandard$Option$AWNXWb7QAIiQHhcA06dPcQRw7jA.INSTANCE);
        }

        public boolean is(Function1<T, Boolean> function1) {
            T t = this.variable;
            if (t != INVALID_VALUE) {
                return function1.invoke(t).booleanValue();
            }
            return false;
        }

        public Option<T> set(T t) {
            if (this.variable != INVALID_VALUE) {
                this.variable = t;
            }
            return this;
        }

        public Option<T> setIf(T t, Function1<T, Boolean> function1) {
            T t2 = this.variable;
            if (t2 != INVALID_VALUE && function1.invoke(t2).booleanValue()) {
                this.variable = t;
            }
            return this;
        }

        public Option<T> setAnyway(T t) {
            this.variable = t;
            return this;
        }

        public T get() {
            T t = this.variable;
            if (t != INVALID_VALUE) {
                return t;
            }
            return null;
        }

        public <NT> Option<NT> let(Function1<T, NT> function1) {
            T t = this.variable;
            T t2 = INVALID_VALUE;
            if (t != t2) {
                return JavaStandard.option(function1.invoke(t));
            }
            return JavaStandard.option(t2);
        }

        public <NT> Option<NT> as(Class<NT> cls) {
            T t = this.variable;
            T t2 = INVALID_VALUE;
            if (t != t2) {
                try {
                    if (t.getClass() == cls) {
                        return JavaStandard.option(this.variable);
                    }
                } catch (Exception unused) {
                    return JavaStandard.option(INVALID_VALUE);
                }
            }
            return JavaStandard.option(t2);
        }
    }
}
