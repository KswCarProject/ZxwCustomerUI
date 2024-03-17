package com.szchoiceway.customerui.kt_like;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class ListUtil {
    public static <T> T getOrNull(List<T> list, int i) {
        if (i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static <T> T getOrDefault(List<T> list, int i, T t) {
        T orNull = getOrNull(list, i);
        return orNull != null ? orNull : t;
    }

    public static <T> T getOrElse(List<T> list, int i, Function0<T> function0) {
        T orNull = getOrNull(list, i);
        if (orNull != null) {
            return orNull;
        }
        return function0.invoke();
    }

    public static <T> T find(List<T> list, Function1<T, Boolean> function1) {
        for (T next : list) {
            if (function1.invoke(next).booleanValue()) {
                return next;
            }
        }
        return null;
    }

    public static <T> List<T> take(List<T> list, int i) {
        return list.subList(0, i);
    }

    public static <T> List<T> takeLast(List<T> list, int i) {
        return list.subList(list.size() - i, list.size());
    }

    public static <T> T first(List<T> list) {
        return list.get(0);
    }

    public static <T> T firstOrNull(List<T> list) {
        return getOrNull(list, 0);
    }

    public static <T> T firstOrDefault(List<T> list, T t) {
        return getOrDefault(list, 0, t);
    }

    public static <T> T firstOrElse(List<T> list, Function0<T> function0) {
        return getOrElse(list, 0, function0);
    }

    public static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> T lastOrNull(List<T> list) {
        return getOrNull(list, list.size() - 1);
    }

    public static <T> T lastOrDefault(List<T> list, T t) {
        return getOrDefault(list, list.size() - 1, t);
    }

    public static <T> T lastOrElse(List<T> list, Function0<T> function0) {
        return getOrElse(list, list.size() - 1, function0);
    }

    public static <K, T> Map<K, List<T>> groupBy(List<T> list, Function1<T, K> function1) {
        HashMap hashMap = new HashMap();
        list.forEach(new Consumer(hashMap) {
            public final /* synthetic */ HashMap f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                ListUtil.lambda$groupBy$0(Function1.this, this.f$1, obj);
            }
        });
        return hashMap;
    }

    static /* synthetic */ void lambda$groupBy$0(Function1 function1, HashMap hashMap, Object obj) {
        List list;
        Object invoke = function1.invoke(obj);
        if (hashMap.containsKey(invoke)) {
            list = (List) hashMap.get(invoke);
        } else {
            ArrayList arrayList = new ArrayList();
            hashMap.put(invoke, arrayList);
            list = arrayList;
        }
        list.add(obj);
    }

    public static <T> List<T> listOf(T... tArr) {
        ArrayList arrayList = new ArrayList(tArr.length);
        arrayList.addAll(Arrays.asList(tArr));
        return arrayList;
    }

    public static <T> ArrayList<T> arrayListOf(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        arrayList.addAll(Arrays.asList(tArr));
        return arrayList;
    }

    public static <T> List<T> listOfNonNull(T... tArr) {
        return filter(listOf(tArr), $$Lambda$ListUtil$CNghoUODPcLNPbRDzbn5mnExck.INSTANCE);
    }

    public static <T> void forEach(List<T> list, Function1Void<T> function1Void) {
        for (T invoke : list) {
            function1Void.invoke(invoke);
        }
    }

    public static <T> List<T> onEach(List<T> list, Function1Void<T> function1Void) {
        forEach(list, function1Void);
        return list;
    }

    public static <T> String joinToString(List<T> list) {
        return joinToString(list, $$Lambda$ListUtil$cy49HjiEWLRGhl1gQrwKKSk35yc.INSTANCE);
    }

    public static <T> String joinToString(List<T> list, Function1<T, String> function1) {
        if (list.isEmpty()) {
            return "";
        }
        return ((StringBuilder) JavaStandard.also(new StringBuilder(), new Function1Void(list, function1) {
            public final /* synthetic */ List f$0;
            public final /* synthetic */ Function1 f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                ListUtil.lambda$joinToString$2(this.f$0, this.f$1, (StringBuilder) obj);
            }
        })).toString();
    }

    static /* synthetic */ void lambda$joinToString$2(List list, Function1 function1, StringBuilder sb) {
        sb.append((String) function1.invoke(first(list)));
        JavaStandard.repeat(list.size() - 1, new Function1Void(sb, function1, list) {
            public final /* synthetic */ StringBuilder f$0;
            public final /* synthetic */ Function1 f$1;
            public final /* synthetic */ List f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void invoke(Object obj) {
                ListUtil.lambda$joinToString$1(this.f$0, this.f$1, this.f$2, (Integer) obj);
            }
        });
    }

    static /* synthetic */ void lambda$joinToString$1(StringBuilder sb, Function1 function1, List list, Integer num) {
        sb.append(", ");
        sb.append((String) function1.invoke(list.get(num.intValue() + 1)));
    }

    public static <T> List<T> filter(List<T> list, Function1<T, Boolean> function1) {
        return (List) JavaStandard.also(new ArrayList(), new Function1Void(list, function1) {
            public final /* synthetic */ List f$0;
            public final /* synthetic */ Function1 f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                ListUtil.forEach(this.f$0, new Function1Void((ArrayList) obj) {
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void invoke(Object obj) {
                        ListUtil.lambda$filter$3(Function1.this, this.f$1, obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ void lambda$filter$3(Function1 function1, ArrayList arrayList, Object obj) {
        if (((Boolean) function1.invoke(obj)).booleanValue()) {
            arrayList.add(obj);
        }
    }

    public static Integer sum(List<Integer> list) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Objects.requireNonNull(atomicInteger);
        forEach(list, new Function1Void(atomicInteger) {
            public final /* synthetic */ AtomicInteger f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                int unused = this.f$0.addAndGet(((Integer) obj).intValue());
            }
        });
        return Integer.valueOf(atomicInteger.get());
    }

    public static <T> void forEachIndexed(List<T> list, Function2Void<T, Integer> function2Void) {
        JavaStandard.repeat(list.size(), new Function1Void(list) {
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                Function2Void.this.invoke(this.f$1.get(((Integer) obj).intValue()), (Integer) obj);
            }
        });
    }

    public static <T> Option<T> option(List<T> list) {
        return new Option<>(list);
    }

    public static class Option<T> {
        private static final ArrayList INVALID_VALUE = new ArrayList();
        private List<T> variable;

        public Option(List<T> list) {
            this.variable = list;
        }

        public Option<T> onEach(Function1Void<T> function1Void) {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                ListUtil.forEach(list, function1Void);
            }
            return this;
        }

        public void forEach(Function1Void<T> function1Void) {
            onEach(function1Void);
        }

        public Option<T> filter(Function1<T, Boolean> function1) {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                this.variable = ListUtil.filter(list, function1);
            }
            return this;
        }

        public Option<T> condition(Function1<List<T>, Boolean> function1) {
            List<T> list = this.variable;
            ArrayList arrayList = INVALID_VALUE;
            if (list != arrayList && !function1.invoke(list).booleanValue()) {
                this.variable = arrayList;
            }
            return this;
        }

        public boolean is(Function1<List<T>, Boolean> function1) {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                return function1.invoke(list).booleanValue();
            }
            return false;
        }

        public Option<T> also(Function1Void<List<T>> function1Void) {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                function1Void.invoke(list);
            }
            return this;
        }

        public Option<T> notEmpty() {
            return condition($$Lambda$ListUtil$Option$5PCyj4jb3qDnBthyoVhGGY3H_c.INSTANCE);
        }

        public Option<T> empty() {
            return condition($$Lambda$ListUtil$Option$mB5cZsB78cUVunoMla0LQQNDSM.INSTANCE);
        }

        public <R> List<R> map(Function1<T, R> function1) {
            if (this.variable == INVALID_VALUE) {
                return null;
            }
            ArrayList arrayList = new ArrayList(this.variable.size());
            ListUtil.forEach(this.variable, new Function1Void(arrayList, function1) {
                public final /* synthetic */ ArrayList f$0;
                public final /* synthetic */ Function1 f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void invoke(Object obj) {
                    this.f$0.add(this.f$1.invoke(obj));
                }
            });
            return arrayList;
        }

        public <R> Option<R> newOption(Function1<T, R> function1) {
            List<R> map = map(function1);
            if (map == null) {
                map = new ArrayList<>();
            }
            return new Option<>(map);
        }

        public String joinToString(Function1<T, String> function1) {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                return ListUtil.joinToString(list, function1);
            }
            return null;
        }

        public Option<T> set(List<T> list) {
            if (list != INVALID_VALUE) {
                this.variable = list;
            }
            return this;
        }

        public List<T> get() {
            List<T> list = this.variable;
            if (list != INVALID_VALUE) {
                return list;
            }
            return null;
        }
    }

    private ListUtil() {
    }
}
