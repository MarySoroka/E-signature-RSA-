public class HashFunction {
    private  int h0;
    private  int n;

    int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    private int hash;

    HashFunction (int n, byte[] text) {
        this.h0 = 100;
        this.n = n;
        this.hash = hash(n,h0,text);

    }
    private int hash(int n, int h0, byte[] text){
        int hash=h0;
        for (byte b : text) {
            hash = ((hash + b) * (hash + b)) % n;
        }
        return hash;
    }

}
