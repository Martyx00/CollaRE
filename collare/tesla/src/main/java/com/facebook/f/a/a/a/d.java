package com.facebook.f.a.a.a;

/* compiled from: ImageOriginUtils */
public class d {
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int a(String str) {
        char c2;
        switch (str.hashCode()) {
            case -1914072202:
                if (str.equals("BitmapMemoryCacheGetProducer")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1307634203:
                if (str.equals("EncodedMemoryCacheProducer")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -1224383234:
                if (str.equals("NetworkFetchProducer")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 656304759:
                if (str.equals("DiskCacheProducer")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 957714404:
                if (str.equals("BitmapMemoryCacheProducer")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 1;
            case 4:
                return 0;
            default:
                return -1;
        }
    }
}
