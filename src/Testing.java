/**
 * Created with IntelliJ IDEA.
 * User: michaelk18
 * Date: 2/11/14
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Testing {

    /*private static SecretKeySpec secretKey;
    private static final int        KEY_LENGTH      = 256;
    private static String key= "6a8875d3e484c1f82862ecd73a691c4b";

    public static void main(String [] args)
    {
        byte b [] = new byte[16];

        try
        {
            Testing.initializeSecretKey();
            byte [] plain    = "Hello world".getBytes();
            byte [] decrypt  = Testing.decrypt(Testing.encrypt("Hello world".getBytes("UTF-8")));



            for(int i =0; i < decrypt.length; i++)
            {
                System.out.print(decrypt[i]) ;
            }

            System.out.println();
            for(int j =0; j < plain.length; j++)
            {
                System.out.print(plain[j]) ;
            }


        }
        catch(Exception e)
        {
            e.toString();
        }
    }

    private static void initializeSecretKey() throws NoSuchAlgorithmException
    {
        if (secretKey == null)
        {


            Testing.secretKey = new SecretKeySpec(key.getBytes(), "AES");

            System.out.println(Testing.secretKey.toString());
            System.out.println();

        }



    }

    private static byte[] encrypt(byte[] clear) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);

        System.out.println("Encrypted is");
        String b = "";
        for(int i =0; i < encrypted.length; i ++)
        {
            b+=encrypted[i] + "";
        }

        System.out.println(b);
        return encrypted;
    }

    private static byte[] decrypt(byte[] encrypted) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }  */
}
