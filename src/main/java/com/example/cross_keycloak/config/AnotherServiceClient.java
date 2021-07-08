//package com.example.cross_keycloak.config;
//
//import org.keycloak.KeycloakPrincipal;
//import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
//import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Component
//public class AnotherServiceClient {
//    public TypeOfObjectReturnedByAnotherService getFromAnotherSer
//    vice() {
//        RestTemplate restTemplate = new RestTemplate();
//        String endpoint = "http://localhost:40030/another/service/url";
//        String bearerToken = getAuthorizationToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "bearer " + bearerToken);
//
//        HttpEntity entity = new HttpEntity(headers);
//
//        ResponseEntity<TypeOfObjectReturnedByAnotherService> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, TypeOfObjectReturnedByAnotherService.class);
//
//        return response.getBody();
//    }
//
//    private String getAuthorizationToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SimpleKeycloakAccount details = (SimpleKeycloakAccount) authentication.getDetails();
//
//        KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) details.getPrincipal();
//
//        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) getPrincipal().getKeycloakSecurityContext();
//
//        return context.getTokenString();
//    }
//}