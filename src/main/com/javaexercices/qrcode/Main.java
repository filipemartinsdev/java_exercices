package qrcode;

public class Main {
    public static void main(String[] args) {
        QRCode myQR = new QRCode(3);
        myQR.printThis();
        myQR.createFile();
        myQR.createPBM();
    }
}
