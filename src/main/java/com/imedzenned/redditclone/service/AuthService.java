package com.imedzenned.redditclone.service;

import com.imedzenned.redditclone.dto.RegisterRequest;
import com.imedzenned.redditclone.exceptions.SrpringRedditException;
import com.imedzenned.redditclone.model.NotificationEmail;
import com.imedzenned.redditclone.model.User;
import com.imedzenned.redditclone.model.VerificationToken;
import com.imedzenned.redditclone.repository.UserRepository;
import com.imedzenned.redditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup (RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Please activate your account",
                user.getEmail(),"clic to activate you account :"
        + "http://localhost:8080/api/auth/accountVerification/"+token));

    }
    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token ;

    }

    public void verityAccount(String token) {
        Optional<VerificationToken> verificationToken =  verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()-> new SrpringRedditException("Invalid token"));
        fetchUserAndEnable(verificationToken.get());
                
    }
    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SrpringRedditException("user name not found "+ username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
