package com.szchoiceway.customerui.kt_like;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

public interface MapUtil {
    static <K, V> void putToList(Map<K, List<V>> map, K k, V v) {
        ((List) JavaStandard.let(map.get(k), new Function1(map, k) {
            public final /* synthetic */ Map f$0;
            public final /* synthetic */ Object f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object invoke(Object obj) {
                return MapUtil.lambda$putToList$1(this.f$0, this.f$1, (List) obj);
            }
        })).add(v);
    }

    static /* synthetic */ List lambda$putToList$1(Map map, Object obj, List list) {
        return list != null ? list : (List) JavaStandard.also(new ArrayList(), new Function1Void(map, obj) {
            public final /* synthetic */ Map f$0;
            public final /* synthetic */ Object f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                this.f$0.put(this.f$1, (ArrayList) obj);
            }
        });
    }

    @SafeVarargs
    static <K, V> Map<K, V> mapOf(Pair<K, V>... pairArr) {
        return (Map) JavaStandard.also(new HashMap(), new Function1Void(pairArr) {
            public final /* synthetic */ Pair[] f$0;

            {
                this.f$0 = r1;
            }

            public final void invoke(Object obj) {
                ArraysUtil.forEach(this.f$0, new Function1Void((HashMap) obj) {
                    public final /* synthetic */ HashMap f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void invoke(Object obj) {
                        this.f$0.put(((Pair) obj).getFirst(), ((Pair) obj).getSecond());
                    }
                });
            }
        });
    }
}
