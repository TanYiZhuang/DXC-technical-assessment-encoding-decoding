import java.util.Scanner;

public class EncoderDecoder {
    private final String referenceTable;
    
    public EncoderDecoder(String referenceTable) {
        this.referenceTable = referenceTable;
        }      
        
    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();
        
        // Get the offset character
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Offset Chac:");
        char offsetChar = sc.nextLine().charAt(0);
        offsetChar = Character.toUpperCase(offsetChar);
        int offset = referenceTable.indexOf(offsetChar);
        encodedText.append(offsetChar);
        // Encode each character in the plaintext
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            int index = referenceTable.indexOf(ch);
            String s = String.valueOf(ch);
            char encodedChar = ch;
            // Shift the character index based on the offset
            if (referenceTable.contains(s)) {
            	int temp = index - offset;
            	if (temp<0) {
            		temp = referenceTable.length()+temp;
            	}
            	int shiftedIndex = (temp)%referenceTable.length();
            	encodedChar = referenceTable.charAt(shiftedIndex);
            }
            else {
            	encodedChar = ch;
            }
            
            encodedText.append(encodedChar);
            
        }
        
        return encodedText.toString();
    }
    
    public String decode(String encodedText) {
        StringBuilder plainText = new StringBuilder();
        
        // Get the offset character
        char offsetChar = encodedText.charAt(0);
        int offset = referenceTable.indexOf(offsetChar);
        // Decode each character in the encoded text
        for (int i = 1; i < encodedText.length(); i++) {
            char ch = encodedText.charAt(i);
            int index = referenceTable.indexOf(ch);
            String s = String.valueOf(ch);
            char decodedChar = ch;
            // Shift the character index based on the offset
            if (referenceTable.contains(s)) {            	            
            	int shiftedIndex = (index + offset) % referenceTable.length();
            	decodedChar = referenceTable.charAt(shiftedIndex);
            }
            else {
            	decodedChar = ch;
            }
            
            plainText.append(decodedChar);
            
        }
        
        return plainText.toString();
    }
    
    public static void main(String[] args) {
        String referenceTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
        EncoderDecoder encoderDecoder = new EncoderDecoder(referenceTable);
        Scanner sc = new Scanner(System.in);
        System.out.println("Input word to encode:");
        String plainText = sc.nextLine();
        plainText = plainText.toUpperCase();
        String encodedText = encoderDecoder.encode(plainText);
        String decodedText = encoderDecoder.decode(encodedText);
        
        System.out.println("Plaintext: " + plainText);
        System.out.println("Encoded Text: " + encodedText);
        System.out.println("Decoded Text: " + decodedText);
    }
}