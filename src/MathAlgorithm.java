import java.util.List;

public interface MathAlgorithm {


    static int EvclidAlgorithm(int a, int b){
        int d0 = a;
        int d1 = b;
        int x0 = 1;
        int x1 = 0;
        int y0 = 0;
        int y1 = 1;
        while(d1>1){
            int q = d0/d1;
            int d2 = d0 % d1;
            int x2 = x0 - q*x1;
            int y2 = y0 - q*y1;
            d0 =d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }
        if (y1>0){
            return y1;}
        else{
            return y1+a;
        }
    }

    static long phi(long n) {
        long result = n;
        for (long i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
    }
     static long gcd(long a, long b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }

     static byte[] int2byte(int[] src) {
        int srcLength = src.length;
        byte[]dst = new byte[srcLength << 2];

        for (int i=0; i<srcLength; i++) {
            int x = src[i];
            int j = i << 2;
            dst[j++] = (byte) ((x) & 0xff);
            dst[j++] = (byte) ((x >>> 8) & 0xff);
            dst[j++] = (byte) ((x >>> 16) & 0xff);
            dst[j++] = (byte) ((x >>> 24) & 0xff);
        }
        return dst;
    }

    static int[] byte2int(byte[] src) {
        int dstLength = src.length >>> 2;
        int[]dst = new int[dstLength];

        for (int i=0; i<dstLength; i++) {
            int j = i << 2;
            int x = 0;
            x += (src[j++] & 0xff);
            x += (src[j++] & 0xff) << 8;
            x += (src[j++] & 0xff) << 16;
            x += (src[j++] & 0xff) << 24;
            dst[i] = x;
        }
        return dst;
    }
    static int power(int a, int z, int m)
    {
        int a1 = a;
        int z1 = z;
        int x = 1;
        while (z1 != 0) {
            while(z1 % 2 == 0) {
                z1 /= 2;
                a1 = (a1*a1) % m;
            }
            z1 = z1 - 1;
            x = (x*a1) % m;
        }
        return x;
    }
}
