package cv_maker.main.core;

import java.security.SecureRandom;

public class VerificationCodeGenerator {

    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }
}



/*
*
* TODO:Hangi ip adreslerinin limitorde olduğunu bir get metoduyla ne kadar saniyeleri kaldığıyla beraber yazdır.
*
* TODO:Limitorün içinde olan ip adresinin süresini sıfırlayan bir endpoint yaz.
*
* */