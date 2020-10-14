package com.example.quickcash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private String password ;

    public PasswordValidator(String password) {
        this.password =  password;
    }

    public boolean isLongEnough() {
        return password.length() >= 8;
    }

    public boolean caseCheck() {
        boolean hasCapital = false;
        boolean  hasLower = false;
        char thisChar;
        for(int currChar=0;currChar < password.length();currChar++) {
            thisChar = password.charAt(currChar);
            if (Character.isUpperCase(thisChar)) {
                hasCapital = true;
            } else if (Character.isLowerCase(thisChar)) {
                hasLower = true;
            }
        }
        return hasCapital && hasLower;
    }

    public boolean specialCheck() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_@$!]*");
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public boolean validate() {
        return isLongEnough() && caseCheck() && specialCheck();
    }

}
