/**
 * Created by minhduong on 2/23/17.
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/*
  Main simulation engine.
  Do not modify class/method names used in supplied code.
  Usage: java DES_Simulation plaintext-filename key-filename
*/

public class DES_Simulation {



    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: java DES_Simulation plaintext-filename key-filename");
//            String a = "10100011010111";
//            String[] arr = a.split("");
//            System.out.print(arr[0]+"\n");
//            String t = "abcdefg";
//            System.out.print(t.substring(6,7));
//            int[] PC_1 = {57,49};
//            System.out.print(PC_1[0]);
//            String test_key = "0000000000000000000000000000000000000000000000000000000000000000";
//            if (test_key.length() == 64) {
//                String [] subkey = KeySchedule.generateSubkeysForEncryption(test_key);
//                System.out.print(subkey[0]);
//            }
//            String s = "10110";
//            String key = "01101";
//            StringBuilder sb = new StringBuilder();
//            for(int i = 0; i < s.length(); i++) {
//                sb.append((s.charAt(i) ^ key.charAt(i)));
//            }
//
//            int a = Integer.parseInt("10100", 2);
//            System.out.print(a+"\n");
//            String result = sb.toString();
//            System.out.print(result+"\n");

//            long t = 1101010;
//            long r = 1001010;
//            System.out.print(t^r);
            return;
        }

        String plaintextFileName = args[0];
        String keyFileName = args[1];

        //Open file to read plaintext and key
        Scanner input0 = new Scanner(new File(plaintextFileName));
        Scanner input1 = new Scanner(new File(keyFileName));

      /*A key of 64-bits*/
        String key = input1.nextLine();

        String recoveredText = "";

        while(input0.hasNextLine()){
         /* Plaintext, a block of 64 bits*/
            String x = Conversion.strToBits(input0.nextLine());
            String y = DES.encrypt(x, key);
            //System.out.println("ciphertext: " + y); //your ciphertext will be examined
            String decrypted = DES.decrypt(y, key);
            System.out.println(decrypted.equals(x) ? "Success" : "Fail");
            recoveredText += Conversion.bitsToStr(decrypted);
        }
        System.out.println("recoverted Text: ");
        System.out.println(recoveredText);


    }
}
