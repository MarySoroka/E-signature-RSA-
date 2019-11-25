import java.io.*;
import java.nio.ByteBuffer;

public class RSA {
    private int p;
    private int q;
    private int r;

    public long getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public long getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public long getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    private int d;
    private int e;
    RSA(int p, int q){
        this.p = p;
        this.q = q;
    }

    public int[] encode (String name,int r,int d,int e) throws IOException {
        this.r = r;
        this.d = d;
        this.e = e;
        byte[] plaintext = readFromFile(name);
        HashFunction hash =new HashFunction(r,plaintext);
        int hashNum = hash.getHash();
        int eSign = MathAlgorithm.power(hashNum, d, r);
        byte[] bytes= ByteBuffer.allocate(4).putInt(eSign).array();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        outStream.write(plaintext);
        outStream.write(bytes);
        byte[] mass = outStream.toByteArray();
        writeInFile(name+".eSign",mass);
        return new int[]{hashNum,eSign};
    }

    public int[] decode (String name,int r, int e) throws IOException {
        this.e = e;
        this.r = r;
        byte[] cipher = readFromFile(name);
        byte[] eSignByte = new byte[]{cipher[cipher.length-4],cipher[cipher.length-3],cipher[cipher.length-2],cipher[cipher.length-1]};
        ByteBuffer wrapped = ByteBuffer.wrap(eSignByte);
        int num = wrapped.getInt();
        byte[] text = new byte[cipher.length-4];
        for(int i = 0; i<cipher.length-4; i++){
            text[i] = cipher[i];
        }
        HashFunction hash = new HashFunction(r,text);
        int hashNum = hash.getHash();
        int eSign = MathAlgorithm.power(num, e, r);
        return new int[]{hashNum,eSign};
    }

    private byte[] readFromFile (String fileName) throws IOException {
        FileInputStream file= new FileInputStream(fileName);
        byte[] text = file.readAllBytes();
        file.close();
        return text;
    }

    private void writeInFile(String name, byte[] text) throws IOException {
        FileOutputStream file = new FileOutputStream(name);
        file.write(text);
        file.close();
    }
}
