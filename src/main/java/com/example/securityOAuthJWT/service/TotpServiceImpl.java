package com.example.securityOAuthJWT.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Service;

@Service
public class TotpServiceImpl implements TotpService {
    private final GoogleAuthenticator gAuth;


    public TotpServiceImpl(GoogleAuthenticator gAuth) {
        this.gAuth = gAuth;
    }

    public TotpServiceImpl() {
        this.gAuth = new GoogleAuthenticator();
    }

    @Override
    public GoogleAuthenticatorKey generateSecretKey(){
        return gAuth.createCredentials();
    }

    @Override
    public String getQRCodeUrl(GoogleAuthenticatorKey secretKey, String username){
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("Secure Note App",username,secretKey);
    }

    @Override
    public boolean verifyQRCode(String secretKey, int code){
        return gAuth.authorize(secretKey,code);
    }

}
