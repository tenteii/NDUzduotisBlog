package com.example.testas.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidacija implements Predicate<String> {
    @Override
    public boolean test(String s) {
        if (s == null || s.isEmpty()){
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(s).matches()) {
            return true;
        }   else {
            return false;
        }
    }
}
