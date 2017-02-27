/**
 * Created by minhduong on 2/26/17.
 */
public class test_case {

    public static void PC_1_test(){
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String expect_result = "11110000110011001010101011110101010101100110011110001111";
        String result = KeySchedule.pc_1(key);
        System.out.print("result_after_pc1: "+result+"\n");
        if (result.equals(expect_result))
            System.out.print("PC_1 test for key generate succedd");
        else
            System.out.print("PC_1 test for key generate fail");
    }




    public static void keyTest() {
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String[] expect_result = {"000110110000001011101111111111000111000001110010",
                                "011110011010111011011001110110111100100111100101",
                                "010101011111110010001010010000101100111110011001",
                "011100101010110111010110110110110011010100011101",
                "011111001110110000000111111010110101001110101000",
                "011000111010010100111110010100000111101100101111",
                "111011001000010010110111111101100001100010111100",
                "111101111000101000111010110000010011101111111011",
                "111000001101101111101011111011011110011110000001",
                "101100011111001101000111101110100100011001001111",
                "001000010101111111010011110111101101001110000110",
                "011101010111000111110101100101000110011111101001",
                "100101111100010111010001111110101011101001000001",
                "010111110100001110110111111100101110011100111010",
                "101111111001000110001101001111010011111100001010",
                "110010110011110110001011000011100001011111110101"};
        String[] keys = KeySchedule.generateSubkeysForEncryption(key);
        for(int i=0; i<16; i++)
        {
            if(keys[i].equals(expect_result[i]))
                System.out.print("succeed at round: "+(i+1));
            else
                System.out.print("fail at round: "+(i+1));
        }
    }

    public static void test_IP(){
        String result = IP.permute("0000000100100011010001010110011110001001101010111100110111101111");
        String expect = "1100110000000000110011001111111111110000101010101111000010101010";
        System.out.print("result match expect IP: "+(result.equals(expect)));
    }

    public static void test_f_funtion(){
        String current_right ="11110000101010101111000010101010";
        String subkey = "000110110000001011101111111111000111000001110010";
        String expect_result = "00100011010010101010100110111011";
        String f_function_result = FeistelNetwork.f_function(current_right, subkey);
        System.out.print("f function result match the expect result: "+f_function_result.equals(expect_result));
    }


    public static void test_result_round_16(){
        String expect_result = "0000101001001100110110011001010101000011010000100011001000110100";
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String[] keys = KeySchedule.generateSubkeysForEncryption(key);
        String input_after_IP = IP.permute("0000000100100011010001010110011110001001101010111100110111101111");
        String round_16_result = FeistelNetwork.iterate(input_after_IP,keys);
        System.out.print("round 16 iterative result match expect_result: "+round_16_result.equals(expect_result));
    }

    public static void test_encryption(){
        String plain_text = "0000000100100011010001010110011110001001101010111100110111101111";
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String expect_result = "1000010111101000000100110101010000001111000010101011010000000101";
        String result = DES.encrypt(plain_text,key);
        System.out.print(result.equals(expect_result));
    }

    public static void test_decryption() {
        String plain_text = "1000010111101000000100110101010000001111000010101011010000000101";
        String expect_result = "0000000100100011010001010110011110001001101010111100110111101111";
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String result = DES.decrypt(plain_text,key);
        System.out.print(result.equals(expect_result));
    }

    public static void test_decryption_key(){
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String[]des_key = KeySchedule.generateSubkeysForDecryption(key);
        String[]en_key = KeySchedule.generateSubkeysForEncryption(key);
        for(int i=0; i<16; i++){
            System.out.print("encryption Key round "+(i+1)+" equal: "+en_key[i]+"\n");
            System.out.print("decryption Key round "+(16-i)+" equal: "+des_key[15-i]+"\n");
            System.out.print("encryption key "+(i+1)+"equal decryption key "+(16-i)+" :"+en_key[i].equals(des_key[15-i])+"\n");

        }
    }
}
