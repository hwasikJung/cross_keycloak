
package com.example.cross_keycloak.controller;

import com.example.cross_keycloak.model.Customer;
import com.example.cross_keycloak.model.CustomerDAO;
import com.example.cross_keycloak.model.UserCredentials;
import com.example.cross_keycloak.service.KeyCloakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    KeyCloakService keyClockService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> getTokenUsingCredentials( UserCredentials userCredentials) {

        String responseToken = null;
        try {

            responseToken = keyClockService.getToken(userCredentials);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseToken, HttpStatus.OK);

    }


    @GetMapping(path = "/")
    public String index() {

        return "external";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {

        addCustomers();
        Iterable<Customer> customers = customerDAO.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {

        Customer customer1 = new Customer();
        customer1.setAddress("1111 foo blvd");
        customer1.setName("Foo Industries");
        customer1.setServiceRendered("Important services");
        customerDAO.save(customer1);

        Customer customer2 = new Customer();
        customer2.setAddress("2222 bar street");
        customer2.setName("Bar LLP");
        customer2.setServiceRendered("Important services");
        customerDAO.save(customer2);

        Customer customer3 = new Customer();
        customer3.setAddress("33 main street");
        customer3.setName("Big LLC");
        customer3.setServiceRendered("Important services");
        customerDAO.save(customer3);
    }

    /*@GetMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCustomers() {

        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();

        AccessToken accessToken = session.getToken();
        String a = principal.getName();
        username = accessToken.getPreferredUsername();
        emailID = accessToken.getEmail();
        lastname = accessToken.getFamilyName();
        firstname = accessToken.getGivenName();
        realmName = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();

        return AccessToken;
    }
*/
}

