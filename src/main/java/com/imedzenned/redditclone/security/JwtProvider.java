package com.imedzenned.redditclone.security;

import com.imedzenned.redditclone.exceptions.SrpringRedditException;
import com.imedzenned.redditclone.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;

@Service
public class JwtProvider {

    public void init(){

    }

    private String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }
    private PrivateKey getPrivateKey(){
        try {
            return (PrivateKey) keyStore.getKey("springblog","secret".toCharArray());
        }catch (KeyStoreException| NoSuchAlgorithmException | UnrecoverableKeyException e){
            throw  new SrpringRedditException("exception occured while retriving public key from keystore");

        }
    }
}
