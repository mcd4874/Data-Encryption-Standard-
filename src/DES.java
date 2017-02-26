/**
 * Created by minhduong on 2/23/17.
 */

/**
 Des encryption algirithm
 */
public class DES {

    /**
     DES encryption algorithm
     @param x 64-bit plaintext
     @param key 64-bit key
     @return 64-bit encrypted text
     */
    public static String encrypt(String x, String key){
        String[] subkeys = KeySchedule.generateSubkeysForEncryption(key);
        String firstOutput = IP.permute(x);
        String FeistelOutput = FeistelNetwork.iterate(firstOutput,subkeys);
      /* Uncomment the following two lines and comment out return x */
        String finalOutput = IPinverse.permute(FeistelOutput);
        return finalOutput;

        //return x;

    }
    /**
     DES decryption algorithm
     @param y 64-bit ciphertext
     @param key 64-bit key
     @return 64-bit decrypted text
     */
    public static String decrypt(String y, String key){
        //Complete this method
        return y;
    }

}
