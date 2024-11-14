package me.amazon.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "bD3g6kF9T1w8hJq2PzR0VtMk4A1uG5Xz9CqZ7V8L6gSxH3B7V9kD2tL0rU3F7jT";
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String createToken(String username) {
        // Преобразование строки ключа в массив байтов
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SIGNATURE_ALGORITHM.getJcaName());
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SIGNATURE_ALGORITHM)
                .compact();
    }
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // Токен действует 1 час
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public String getUsernameFromToken(String token) {
        JwtParser jwtParser = Jwts.parser()  // Используем parserBuilder вместо старого parser()
                .setSigningKey(SECRET_KEY)
                .build();

        return jwtParser.parseClaimsJws(token)  // Парсим токен
                .getBody()
                .getSubject();  // Возвращаем имя пользователя
    }
}
