package com.txznet.weather;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IQueryService extends IInterface {

    public static class Default implements IQueryService {
        public IBinder asBinder() {
            return null;
        }

        public byte[] sendInvoke(String str, String str2, byte[] bArr) throws RemoteException {
            return null;
        }
    }

    byte[] sendInvoke(String str, String str2, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IQueryService {
        private static final String DESCRIPTOR = "com.txznet.weather.IQueryService";
        static final int TRANSACTION_sendInvoke = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQueryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IQueryService)) {
                return new Proxy(iBinder);
            }
            return (IQueryService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                byte[] sendInvoke = sendInvoke(parcel.readString(), parcel.readString(), parcel.createByteArray());
                parcel2.writeNoException();
                parcel2.writeByteArray(sendInvoke);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IQueryService {
            public static IQueryService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public byte[] sendInvoke(String str, String str2, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendInvoke(str, str2, bArr);
                    }
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    obtain2.recycle();
                    obtain.recycle();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQueryService iQueryService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iQueryService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iQueryService;
                return true;
            }
        }

        public static IQueryService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
