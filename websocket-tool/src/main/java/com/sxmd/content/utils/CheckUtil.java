package com.sxmd.content.utils;

import com.sxmd.WebsocketToolApplication;
import org.springframework.util.DigestUtils;

import java.util.prefs.Preferences;

import static com.sxmd.content.utils.RsaUtil.*;

/**
 * Description: 验证
 *
 * @author cy
 * @date 2019年11月04日 16:57
 * Version 1.0
 */
public class CheckUtil {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDwUbx5yxgH7EA3Hyi02euVG54ueQwYPlyeBRMFHXNzpEKcffcK1oy7aqxxns/ZD6//p2/GTY13h15WSftmeIXJyX23ZAJ5lEeZoYjrAHFRvBfp8/b95Fig1rEkDKIdHUMiX8wCyR4IO6rDhTu1bjhi+36QfRPSZdu0w8r+wjF6FwIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAPBRvHnLGAfsQDcfKLTZ65Ubni55DBg+XJ4FEwUdc3OkQpx99wrWjLtqrHGez9kPr/+nb8ZNjXeHXlZJ+2Z4hcnJfbdkAnmUR5mhiOsAcVG8F+nz9v3kWKDWsSQMoh0dQyJfzALJHgg7qsOFO7VuOGL7fpB9E9Jl27TDyv7CMXoXAgMBAAECgYAYSiqipMRpCJf0+lFC9kO5bnEugU2XpbX2wsyJV9Czf4a8Cn9NuSHAT6feVA7uAYfobyP9BxmlWylNz2x5jvT2nh4i75MSFLQBWdZnS8WlMA2y65cUglJywyQDhV/UCipZvxEr3WFkYNFRYqA3r/kqqZnfW9hBOGKxflb851/QAQJBAP8KODwB1sZdadxqB4zx2i7WHL4Jg/TXPpdVtHwBmSEK5mPRQs9Sl4t9opwWZLagsxurHu4XjnIhUaqwK4ji3lcCQQDxOVST+6AHziBk+b5EAKqnvB8X3CiRPwXlbvc9oz5XzH0qjX8arHRQHFkHYiJVPZ03dRtiyaxuAvizXcqhUWpBAkEAoGnIJw4tDcWRoR5bs24ngpAPsgQAKI7+AmfYxqqmqx7U/HGRqR9UzFrwXWbEYA5BuaRvJuhQIz8Lw+Vc7JNtrwJAaF0HbKbWy8mwVj9+w+kN7FgFk8saegysPmFN8orn9BByrrBdJ2spP2ekVP9uHzbzCAVQfeMHCOmcM+4GNtkEgQJASd1SAovOZfiEjJ4pDO1y4z0XAOR+wA4eOQbHjTMUrTQPXZHMRFtGWAmMVfDeN5c76wJ3Y362Txa0FjO9p7XcJQ==";
    /***
     * Description:   cpu和网卡进行加密得到的数据
     *
     * @param :
     * @return java.lang.String
     * @author cy
     * @date 2019/11/4 17:13
     */
    public static String encry() throws Exception{
        String cpuType = ComputerInfoUtil.getCpuType();
        String netMac = ComputerInfoUtil.getNetMac();
        String encode = DigestUtils.md5DigestAsHex((cpuType + netMac).getBytes());
        String msg = "websocket-" + encode;
        return msg;
    }

    /**
     * Description:   验证签名
     *
     * @param sign:
     * @return boolean
     * @author cy
     * @date 2019/11/4 17:19
     */
    public static boolean verify(String sign) throws Exception{
        return RsaUtil.verify("cy"+encry(),getPublicKey(publicKey),sign);
    };

    public static String usePulicKeyEncry() throws Exception{
        return encrypt(encry(), getPublicKey(publicKey));
    }

    /**
     *
     * @param privateKey  私钥 签名
     * @param data
     * @return
     * @throws Exception
     */
    public static String getSign(String privateKey,String data) throws Exception{
        // 解密
        String decrypt = decrypt(data, getPrivateKey(privateKey));
        // 签名
        String dataMesg = "cy" + decrypt;
        String sign = sign(dataMesg, getPrivateKey(privateKey));
        return sign;
    }

    public static void main(String[] args) throws Exception{
        String data = "rpCmJn8urDQxU6k5QGs3oBKoedv2b+HOauMdTJE5W6S6lkj9mKcLQ4D0rEqUm6T/n4zecbLePT0OBzPtTx+BOUC6SLCeKJoZYfIoqJYzyPriG5tTkAxgKWlA8dZalqyMvjWXdLXjs1VbhEVHQYrH4TVeAWgp+uFJhk+eXC54LXM=";
        String sign = getSign(privateKey, data);
        System.out.println(sign);
    }

    /**
     * Description:   注册信息
     *
     * @param :
     * @return void
     * @author cy
     * @date 2019/11/4 18:21
     */
    public static boolean registered(String sign){
        Preferences preferences = Preferences.userNodeForPackage(WebsocketToolApplication.class);
        try {
            if(verify(sign)){
                preferences.putBoolean("verify", true);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
