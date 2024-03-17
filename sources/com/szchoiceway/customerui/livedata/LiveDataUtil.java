package com.szchoiceway.customerui.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.szchoiceway.customerui.kt_like.Function1Void;
import com.szchoiceway.customerui.kt_like.JavaStandard;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

public class LiveDataUtil {
    public static <F, S, R> LiveData<R> unionLiveData(DefaultValueLiveData<F> defaultValueLiveData, DefaultValueLiveData<S> defaultValueLiveData2, Function2<F, S, R> function2) {
        return (LiveData) JavaStandard.also(new MediatorLiveData(), new Function1Void(function2, defaultValueLiveData2) {
            public final /* synthetic */ Function2 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void invoke(Object obj) {
                LiveDataUtil.lambda$unionLiveData$2(DefaultValueLiveData.this, this.f$1, this.f$2, (MediatorLiveData) obj);
            }
        });
    }

    static /* synthetic */ void lambda$unionLiveData$2(DefaultValueLiveData defaultValueLiveData, Function2 function2, DefaultValueLiveData defaultValueLiveData2, MediatorLiveData mediatorLiveData) {
        mediatorLiveData.addSource(defaultValueLiveData.liveData, new Observer(function2, defaultValueLiveData2) {
            public final /* synthetic */ Function2 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(obj, this.f$2.getValue()));
            }
        });
        mediatorLiveData.addSource(defaultValueLiveData2.liveData, new Observer(function2, defaultValueLiveData) {
            public final /* synthetic */ Function2 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), obj));
            }
        });
    }

    public static <F, S, T, R> LiveData<R> unionLiveData(DefaultValueLiveData<F> defaultValueLiveData, DefaultValueLiveData<S> defaultValueLiveData2, DefaultValueLiveData<T> defaultValueLiveData3, Function3<F, S, T, R> function3) {
        return (LiveData) JavaStandard.also(new MediatorLiveData(), new Function1Void(function3, defaultValueLiveData2, defaultValueLiveData3) {
            public final /* synthetic */ Function3 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void invoke(Object obj) {
                LiveDataUtil.lambda$unionLiveData$6(DefaultValueLiveData.this, this.f$1, this.f$2, this.f$3, (MediatorLiveData) obj);
            }
        });
    }

    static /* synthetic */ void lambda$unionLiveData$6(DefaultValueLiveData defaultValueLiveData, Function3 function3, DefaultValueLiveData defaultValueLiveData2, DefaultValueLiveData defaultValueLiveData3, MediatorLiveData mediatorLiveData) {
        mediatorLiveData.addSource(defaultValueLiveData.liveData, new Observer(function3, defaultValueLiveData2, defaultValueLiveData3) {
            public final /* synthetic */ Function3 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(obj, this.f$2.getValue(), this.f$3.getValue()));
            }
        });
        mediatorLiveData.addSource(defaultValueLiveData2.liveData, new Observer(function3, defaultValueLiveData, defaultValueLiveData3) {
            public final /* synthetic */ Function3 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), obj, this.f$3.getValue()));
            }
        });
        mediatorLiveData.addSource(defaultValueLiveData3.liveData, new Observer(function3, defaultValueLiveData, defaultValueLiveData2) {
            public final /* synthetic */ Function3 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), this.f$3.getValue(), obj));
            }
        });
    }

    public static <F, S, T, P4, R> LiveData<R> unionLiveData(DefaultValueLiveData<F> defaultValueLiveData, DefaultValueLiveData<S> defaultValueLiveData2, DefaultValueLiveData<T> defaultValueLiveData3, DefaultValueLiveData<P4> defaultValueLiveData4, Function4<F, S, T, P4, R> function4) {
        return (LiveData) JavaStandard.also(new MediatorLiveData(), new Function1Void(function4, defaultValueLiveData2, defaultValueLiveData3, defaultValueLiveData4) {
            public final /* synthetic */ Function4 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;
            public final /* synthetic */ DefaultValueLiveData f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void invoke(Object obj) {
                LiveDataUtil.lambda$unionLiveData$11(DefaultValueLiveData.this, this.f$1, this.f$2, this.f$3, this.f$4, (MediatorLiveData) obj);
            }
        });
    }

    static /* synthetic */ void lambda$unionLiveData$11(DefaultValueLiveData defaultValueLiveData, Function4 function4, DefaultValueLiveData defaultValueLiveData2, DefaultValueLiveData defaultValueLiveData3, DefaultValueLiveData defaultValueLiveData4, MediatorLiveData mediatorLiveData) {
        MediatorLiveData mediatorLiveData2 = mediatorLiveData;
        Function4 function42 = function4;
        DefaultValueLiveData defaultValueLiveData5 = defaultValueLiveData3;
        DefaultValueLiveData defaultValueLiveData6 = defaultValueLiveData4;
        mediatorLiveData.addSource(defaultValueLiveData.liveData, new Observer(function42, defaultValueLiveData2, defaultValueLiveData5, defaultValueLiveData6) {
            public final /* synthetic */ Function4 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;
            public final /* synthetic */ DefaultValueLiveData f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(obj, this.f$2.getValue(), this.f$3.getValue(), this.f$4.getValue()));
            }
        });
        DefaultValueLiveData defaultValueLiveData7 = defaultValueLiveData;
        mediatorLiveData.addSource(defaultValueLiveData2.liveData, new Observer(function42, defaultValueLiveData7, defaultValueLiveData5, defaultValueLiveData6) {
            public final /* synthetic */ Function4 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;
            public final /* synthetic */ DefaultValueLiveData f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), obj, this.f$3.getValue(), this.f$4.getValue()));
            }
        });
        mediatorLiveData.addSource(defaultValueLiveData3.liveData, new Observer(function42, defaultValueLiveData7, defaultValueLiveData2, defaultValueLiveData6) {
            public final /* synthetic */ Function4 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;
            public final /* synthetic */ DefaultValueLiveData f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), this.f$3.getValue(), obj, this.f$4.getValue()));
            }
        });
        mediatorLiveData.addSource(defaultValueLiveData4.liveData, new Observer(function4, defaultValueLiveData, defaultValueLiveData2, defaultValueLiveData3) {
            public final /* synthetic */ Function4 f$1;
            public final /* synthetic */ DefaultValueLiveData f$2;
            public final /* synthetic */ DefaultValueLiveData f$3;
            public final /* synthetic */ DefaultValueLiveData f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue(this.f$1.invoke(this.f$2.getValue(), this.f$3.getValue(), this.f$4.getValue(), obj));
            }
        });
    }
}
