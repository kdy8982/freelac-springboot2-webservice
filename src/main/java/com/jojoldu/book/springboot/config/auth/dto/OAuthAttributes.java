package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes { // 생성시점 : 처음 가입할 때이다. 클래스 생성이 끝났으면, SessionUser.class를 생성해야 한다.
    private Map<String, Object> attributes;
    private String nameAttirbuteKey;
    private String name;
    private String email;
    private String picture;


    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttirbuteKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttirbuteKey = nameAttirbuteKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);

    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttirbuteKey(userNameAttributeName)
                .build();
    }


    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
