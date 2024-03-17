package com.szchoiceway.customerui.kt_like;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataClassHelper {
    public static String toString(Object obj) {
        return toString(obj, getSummit(obj));
    }

    public static String toString(Object obj, Class cls) {
        if (obj == null) {
            return null + "";
        }
        return JavaStandard.buildString(new Function1Void(obj, cls) {
            public final /* synthetic */ Object f$0;
            public final /* synthetic */ Class f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                DataClassHelper.lambda$toString$3(this.f$0, this.f$1, (StringBuilder) obj);
            }
        });
    }

    static /* synthetic */ void lambda$toString$3(Object obj, Class cls, StringBuilder sb) {
        ArrayList arrayList = new ArrayList();
        ListUtil.forEach(getClassList(obj.getClass(), cls), new Function1Void(obj, arrayList) {
            public final /* synthetic */ Object f$0;
            public final /* synthetic */ ArrayList f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                ArraysUtil.forEach(((Class) obj).getDeclaredFields(), new Function1Void(this.f$0, this.f$1) {
                    public final /* synthetic */ Object f$0;
                    public final /* synthetic */ ArrayList f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void invoke(Object obj) {
                        DataClassHelper.lambda$toString$0(this.f$0, this.f$1, (Field) obj);
                    }
                });
            }
        });
        sb.append(obj.getClass().getSimpleName());
        sb.append("(");
        sb.append(ListUtil.joinToString(arrayList, $$Lambda$DataClassHelper$Cu9ZcRSeurZ2KbgfKoD_Bs3AxQ.INSTANCE));
        sb.append(")");
    }

    static /* synthetic */ void lambda$toString$0(Object obj, ArrayList arrayList, Field field) {
        String str;
        field.setAccessible(true);
        String name = field.getName();
        try {
            Object obj2 = field.get(obj);
            if (obj2 instanceof DataClass) {
                str = toString(obj2, ((DataClass) obj2).getSummitClass());
            } else {
                str = StringUtil.safeToString(field.get(obj));
            }
            arrayList.add(new JavaPair(name, str));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ String lambda$toString$2(JavaPair javaPair) {
        return ((String) javaPair.first) + "=" + ((String) javaPair.second);
    }

    public static <T> T copy(T t) {
        return copy(t, getSummit(t));
    }

    public static <T> T copy(T t, Class cls) {
        T t2;
        try {
            t2 = t.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            t2 = null;
        }
        ListUtil.forEach(getClassList(t.getClass(), cls), new Function1Void(t, t2) {
            public final /* synthetic */ Object f$0;
            public final /* synthetic */ Object f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                ArraysUtil.forEach(((Class) obj).getDeclaredFields(), new Function1Void(this.f$0, this.f$1) {
                    public final /* synthetic */ Object f$0;
                    public final /* synthetic */ Object f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void invoke(Object obj) {
                        DataClassHelper.lambda$copy$4(this.f$0, this.f$1, (Field) obj);
                    }
                });
            }
        });
        return t2;
    }

    static /* synthetic */ void lambda$copy$4(Object obj, Object obj2, Field field) {
        field.setAccessible(true);
        try {
            Object obj3 = field.get(obj);
            if (obj3 == null) {
                return;
            }
            if (obj3 instanceof DataClass) {
                field.set(obj2, copy(obj3, ((DataClass) obj3).getSummitClass()));
            } else {
                field.set(obj2, obj3);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> boolean equals(T t, T t2) {
        return equals(t, t2, getSummit(t));
    }

    public static <T> boolean equals(T t, T t2, Class cls) {
        if (t == null || t2 == null || !EqualsUtil.equals((Object) t.getClass().getName(), (Object) t2.getClass().getName())) {
            return false;
        }
        List<Class> classList = getClassList(t.getClass(), cls);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ListUtil.forEach(classList, new Function1Void(t, arrayList, t2, arrayList2) {
            public final /* synthetic */ Object f$0;
            public final /* synthetic */ List f$1;
            public final /* synthetic */ Object f$2;
            public final /* synthetic */ List f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void invoke(Object obj) {
                ArraysUtil.forEach(((Class) obj).getDeclaredFields(), new Function1Void(this.f$0, this.f$1, this.f$2, this.f$3) {
                    public final /* synthetic */ Object f$0;
                    public final /* synthetic */ List f$1;
                    public final /* synthetic */ Object f$2;
                    public final /* synthetic */ List f$3;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void invoke(Object obj) {
                        DataClassHelper.lambda$equals$6(this.f$0, this.f$1, this.f$2, this.f$3, (Field) obj);
                    }
                });
            }
        });
        return EqualsUtil.equals(arrayList, arrayList2, $$Lambda$DataClassHelper$fUIXFxXMaQr5cqQ4VyvqAwyI.INSTANCE);
    }

    static /* synthetic */ void lambda$equals$6(Object obj, List list, Object obj2, List list2, Field field) {
        String str;
        String str2 = "";
        field.setAccessible(true);
        try {
            str = StringUtil.safeToString(field.get(obj));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            str = str2;
        }
        list.add(new JavaPair(field, str));
        try {
            str2 = StringUtil.safeToString(field.get(obj2));
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        list2.add(new JavaPair(field, str2));
    }

    static /* synthetic */ Boolean lambda$equals$8(JavaPair javaPair, JavaPair javaPair2) {
        if (!(javaPair instanceof DataClass) || !(javaPair2 instanceof DataClass)) {
            return Boolean.valueOf(EqualsUtil.equals((Object) javaPair, (Object) javaPair2));
        }
        return Boolean.valueOf(equals(javaPair, javaPair2, ((DataClass) javaPair).getSummitClass()));
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return hashCode(obj, getSummit(obj));
    }

    public static int hashCode(Object obj, Class cls) {
        List<Class> classList = getClassList(obj.getClass(), cls);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ListUtil.forEach(classList, new Function1Void(obj, arrayList2, arrayList) {
            public final /* synthetic */ Object f$0;
            public final /* synthetic */ List f$1;
            public final /* synthetic */ List f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void invoke(Object obj) {
                ArraysUtil.forEach(((Class) obj).getDeclaredFields(), new Function1Void(this.f$0, this.f$1, this.f$2) {
                    public final /* synthetic */ Object f$0;
                    public final /* synthetic */ List f$1;
                    public final /* synthetic */ List f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void invoke(Object obj) {
                        DataClassHelper.lambda$hashCode$9(this.f$0, this.f$1, this.f$2, (Field) obj);
                    }
                });
            }
        });
        Object[] objArr = new Object[arrayList.size()];
        JavaStandard.repeat(arrayList.size(), new Function1Void(objArr, arrayList) {
            public final /* synthetic */ Object[] f$0;
            public final /* synthetic */ List f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                DataClassHelper.lambda$hashCode$11(this.f$0, this.f$1, (Integer) obj);
            }
        });
        int[] iArr = {Arrays.deepHashCode(objArr)};
        if (arrayList2.isEmpty()) {
            return iArr[0];
        }
        ListUtil.forEach(arrayList2, new Function1Void(iArr, objArr) {
            public final /* synthetic */ int[] f$0;
            public final /* synthetic */ Object[] f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void invoke(Object obj) {
                DataClassHelper.lambda$hashCode$12(this.f$0, this.f$1, (DataClass) obj);
            }
        });
        return iArr[0];
    }

    static /* synthetic */ void lambda$hashCode$9(Object obj, List list, List list2, Field field) {
        field.setAccessible(true);
        try {
            Object obj2 = field.get(obj);
            if (obj2 instanceof DataClass) {
                list.add((DataClass) obj2);
            } else if (obj2 != null) {
                list2.add(obj2);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$hashCode$11(Object[] objArr, List list, Integer num) {
        objArr[num.intValue()] = list.get(num.intValue());
    }

    static /* synthetic */ void lambda$hashCode$12(int[] iArr, Object[] objArr, DataClass dataClass) {
        iArr[0] = (iArr[0] * 31) + hashCode(objArr, dataClass.getSummitClass());
    }

    private static Class getSummit(Object obj) {
        DataClass dataClass = obj instanceof DataClass ? (DataClass) obj : null;
        if (dataClass != null) {
            return dataClass.getSummitClass();
        }
        if (obj != null) {
            return obj.getClass();
        }
        return null;
    }

    private static List<Class> getClassList(Class cls, Class cls2) {
        if (cls2 == null) {
            return ListUtil.listOf(cls);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(cls);
        while (cls != cls2) {
            cls = cls.getSuperclass();
            arrayList.add(cls);
        }
        return arrayList;
    }
}
