package fi.livi.like.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fi.livi.like.backend.data.DataRepository;
import fi.livi.like.backend.data.UserRepository;
import fi.livi.like.backend.domain.Location;
import fi.livi.like.backend.domain.User;

@RestController
@RequestMapping(value="backend")
public class LikeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataRepository dataRepository;
    
    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addLocations(@RequestBody Location[] locations) {
        dataRepository.addLocations(locations);
    }
    
    @RequestMapping(value="/getuser", method=RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestBody User user) {
        return userRepository.getUser(user.getUsername(), user.getPassword()); 
    }
}
