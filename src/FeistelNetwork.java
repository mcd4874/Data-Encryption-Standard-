/**
 * Created by minhduong on 2/23/17.
 */

/**
 FeistelNetwork consists of 16 rounds.
 Each round performs identical operations.
 */

public class FeistelNetwork {

    private static final int rounds = 16;

    private static final int[] Expansion = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };

    private static final byte[][][] S_Box = { {
            {14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7},
            {0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8},
            {4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0},
            {15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13}
    }, {
            {15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10},
            {3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5},
            {0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15},
            {13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9}
    }, {
            {10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8},
            {13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1},
            {13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7},
            {1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12}
    }, {
            {7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15},
            {13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9},
            {10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4},
            {3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14}
    }, {
            {2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9},
            {14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6},
            {4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14},
            {11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3}
    }, {
            {12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11},
            {10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8},
            {9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6},
            {4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13}
    }, {
            {4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1},
            {13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6},
            {1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2},
            {6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12}
    }, {
            {13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7},
            {1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2},
            {7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8},
            {2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11}
    } };


    private static final byte[] Permutation = {
            16, 7,  20, 21,
            29, 12, 28, 17,
            1,  15, 23, 26,
            5,  18, 31, 10,
            2,  8,  24, 14,
            32, 27, 3,  9,
            19, 13, 30, 6,
            22, 11, 4,  25
    };

    protected static String xor (String first_input, String second_input){
        StringBuilder result = new StringBuilder();
            for(int i = 0; i < first_input.length(); i++) {
                result.append((first_input.charAt(i) ^ second_input.charAt(i)));
            }
        return result.toString();

    }

    protected static String toBinary(int n) {
        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }
        if (binary.length()!= 4){
            String new_binary = "";
            for (int i=0; i<(4-binary.length()); i++)
                new_binary+="0";
            new_binary+=binary;
            return new_binary;
        }

        return binary;
    }


    private static String s_box_substitute(int s_box ,String input) {
        String row_component = ""+input.charAt(0)+input.charAt(5);
        String column_component = ""+input.substring(1,5);
        int row = Integer.parseInt(row_component, 2);
        int column = Integer.parseInt(column_component, 2);
        int s_box_output_decimal = S_Box[s_box][row][column];
        return toBinary(s_box_output_decimal);
    }

    /**
     Substitution step in f_ function.
     transfer a 48 bits into a 32 bits
     @param input 48-bits string
     @return a 32-bit string generated aftr substitute in S-box.
     */
    private static String substitution(String input) {
        String result = "";
        //split the input string into 8 6bits string
        for(int i=0; i<8; i++) {
            String s_box_input_string = input.substring(i*6,(i+1)*6);
            String s_box_out_put_string = s_box_substitute(i,s_box_input_string);
            result+= s_box_out_put_string;
        }
        return result;
    }

    protected static String permutation(String input) {
        String result ="";
        for (int i=0; i<input.length(); i++)
            result+= input.charAt(Permutation[i]-1);
        return result;
    }

    protected static String f_function(String right_input, String key){
        //expansion
        String right_input_after_expansion = "";
        for (int i =0; i<Expansion.length; i++)
            right_input_after_expansion += right_input.charAt(Expansion[i]-1);
        //perform xor
        String result_after_xor = xor(right_input_after_expansion,key);
        //substitution into s-box
        String result_after_substitution = substitution(result_after_xor);
        //permutate the string after substitution
        return permutation(result_after_substitution);
    }

    /**
     The main part of DES encryption
     In each round, left-half of the input is XORed with the output
     of the f funciton, and then the two halves are swapped.
     @param input 64-bit output of IP
     @param subkeys an array of 16 subkeys
     @return a 64-bit string generated after round 16.
     */
    public static String iterate(String input, String[] subkeys){
        String current_left = input.substring(0,32);
        String current_right = input.substring(32,64);
        for (int round=0; round< rounds; round++)
        {
            String f_function_result = f_function(current_right, subkeys[round]);
            String temp_left = current_left;
            current_left = current_right;
            current_right = xor(temp_left, f_function_result);}
        //after you finish 16 rounds, you need to use reverse logic
        String result = ""+current_right+current_left;
        return result;
    }

}
