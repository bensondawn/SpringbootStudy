package com.jiaxun.Utils;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**Determine encrypt algorithm MD5*/
    private static final String MD5 = "MD5";
    /**UTF-8 Encoding*/
    private static final String UTF_8 = "UTF-8";

    /**
     * MD5 16bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    public static final String MD5_16bit(String readyEncryptStr) throws NoSuchAlgorithmException{
        if(readyEncryptStr != null){
            return MD5Utils.MD5_32bit(readyEncryptStr).substring(8, 24);
        }else{
            return null;
        }
    }

    /**
     * MD5 32bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    public static final String MD5_32bit(String readyEncryptStr) throws NoSuchAlgorithmException {
        if(readyEncryptStr != null){
            //Get MD5 digest algorithm's MessageDigest's instance.
            MessageDigest md = MessageDigest.getInstance(MD5);
            //Use specified byte update digest.
            md.update(readyEncryptStr.getBytes());
            //Get cipher text
            byte [] b = md.digest();
            //The cipher text converted to hexadecimal string
            StringBuilder su = new StringBuilder();
            //byte array switch hexadecimal number.
            for(int offset = 0,bLen = b.length; offset < bLen; offset++){
                String haxHex = Integer.toHexString(b[offset] & 0xFF);
                if(haxHex.length() < 2){
                    su.append("0");
                }
                su.append(haxHex);
            }
            return su.toString();
        }else{
            return null;
        }
    }

    /**
     * MD5 32bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    public static final String MD5_32bit1(String readyEncryptStr) throws NoSuchAlgorithmException{
        if(readyEncryptStr != null){
            //The cipher text converted to hexadecimal string
            StringBuilder su = new StringBuilder();
            //Get MD5 digest algorithm's MessageDigest's instance.
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte [] b = md.digest(readyEncryptStr.getBytes());
            int temp = 0;
            //byte array switch hexadecimal number.
            for(int offset = 0,bLen = b.length; offset < bLen; offset++){
                temp = b[offset];
                if(temp < 0){
                    temp += 256;
                }
                int d1 = temp / 16;
                int d2 = temp % 16;
                su.append(Integer.toHexString(d1) + Integer.toHexString(d2)) ;
            }
            return su.toString();
        }else{
            return null;
        }
    }

    /**
     * MD5 32bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * */
    public static final String MD5_32bit2(String readyEncryptStr) throws NoSuchAlgorithmException{
        if(readyEncryptStr != null){
            //The cipher text converted to hexadecimal string
            StringBuilder su = new StringBuilder();
            //Get MD5 digest algorithm's MessageDigest's instance.
            MessageDigest md = MessageDigest.getInstance(MD5);
            //Use specified byte update digest.
            md.update(readyEncryptStr.getBytes());
            byte [] b = md.digest();
            int temp = 0;
            //byte array switch hexadecimal number.
            for(int offset = 0,bLen = b.length; offset < bLen; offset++){
                temp = b[offset];
                if(temp < 0){
                    temp += 256;
                }
                if(temp < 16){
                    su.append("0");
                }
                su.append(Integer.toHexString(temp));
            }
            return su.toString();
        }else{
            return null;
        }
    }

    public static final BigInteger MD5_32bit3(String readyEncryptStr) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(readyEncryptStr.getBytes());
        byte[] b = md.digest();
        BigInteger bigInteger = new BigInteger(1,b);
        return bigInteger;
    }


    /**
     * MD5 64bit Encrypt Methods.
     * @param readyEncryptStr ready encrypt string
     * @return String encrypt result string
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * */
    public static final String MD5_64bit(String readyEncryptStr) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance(MD5);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String md5_64 = base64Encoder.encode(md.digest(readyEncryptStr.getBytes(UTF_8)));
        return md5_64;
    }

    public static final String MD5_64bit1(String readyEncryptStr) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance(MD5);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String md5_64 = base64Encoder.encode(md.digest(readyEncryptStr.getBytes(UTF_8)));

        BigInteger bigInteger = new BigInteger(1,md5_64.getBytes());
        return bigInteger.toString(16).toUpperCase();
    }

    public static void main(String[] args) {
        try {
            String md516 = MD5Utils.MD5_16bit("kaka123");
            System.out.println("16bit-md5:\n" + md516); //
            String md532 = MD5Utils.MD5_32bit("kaka123");
            String md5321 = MD5Utils.MD5_32bit1("kaka123");
            String md5322 = MD5Utils.MD5_32bit2("kaka123");
            System.out.println("32bit-md5:"); //5d052f1e32af4e4ac2544a5fc2a9b992
            System.out.println("1:  " + md532);
            System.out.println("2:  " + md5321);
            System.out.println("3:  " + md5322);
            String md564 = MD5Utils.MD5_64bit("kaka123");
            System.out.println("64bit-md5:\n" + md564); //
            String md5641 = MD5_64bit1("kaka123");
            System.out.println("64bit-md5_1:\n" + md5641);
            System.out.println(DigestUtils.md5DigestAsHex("kaka123".getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
