package com.mapzen.valhalla;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/mapzen/valhalla/ValhallaRouter$fetch$1", "Lretrofit2/Callback;", "", "(Lcom/mapzen/valhalla/ValhallaRouter;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "library_release"}, k = 1, mv = {1, 1, 1})
/* compiled from: ValhallaRouter.kt */
public final class ValhallaRouter$fetch$1 implements Callback<String> {
    final /* synthetic */ ValhallaRouter this$0;

    ValhallaRouter$fetch$1(ValhallaRouter valhallaRouter) {
        this.this$0 = valhallaRouter;
    }

    public void onResponse(Call<String> call, Response<String> response) {
        Unit unit;
        if (response == null) {
            return;
        }
        if (!response.isSuccessful() || response.body() == null) {
            RouteCallback access$getCallback$p = this.this$0.callback;
            if (access$getCallback$p != null) {
                access$getCallback$p.failure(response.raw().code());
                return;
            }
            return;
        }
        String body = response.body();
        if (body != null) {
            String str = body;
            RouteCallback access$getCallback$p2 = this.this$0.callback;
            if (access$getCallback$p2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(str, "it");
                access$getCallback$p2.success(new Route(str));
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Unit unit2 = unit;
        }
    }

    public void onFailure(Call<String> call, Throwable th) {
        if (th == null) {
            return;
        }
        if (th != null) {
            th.printStackTrace();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Throwable");
    }
}
