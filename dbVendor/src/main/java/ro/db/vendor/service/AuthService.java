package ro.db.vendor.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Roles;

@Service
public class AuthService {

  public static Employees user;
  String key = "1231qdsasdas123123";
  private Key signingKey;

  public AuthService() {
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
    signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
  }

  public static Employees getUser() {
    return user;
  }

  private static void setUser(Employees user) {
    AuthService.user = user;
  }

  public String createJWT(Employees user) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    Map<String, Object> map = new HashMap<>();
    map.put("employeeId", user.getIdEmp());
    map.put("email", user.getEmail());
    map.put("name", user.getEmpName());
    map.put("idRole", user.getRolesByIdRole().getIdRole());

    JwtBuilder builder = Jwts.builder()
        .setSubject(new JSONObject(map).toString())
        .signWith(signatureAlgorithm, signingKey);

    return builder.compact();
  }

  public Employees authenticatedUser(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(signingKey)
        .parseClaimsJws(token)
        .getBody();
    JSONObject subject = new JSONObject(claims.getSubject());
    AuthService.user = new Employees();
    user.setIdEmp((int) subject.get("employeeId"));
    user.setEmail((String) subject.get("email"));
    Roles role = new Roles();
    role.setIdRole((int) subject.get("idRole"));
    user.setRolesByIdRole(role);
    user.setEmpName((String) subject.get("name"));
    return user;
  }
}