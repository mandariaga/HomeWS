package org.rima.controller;

import org.rima.model.Shop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/home")
public class HomeController {
	@RequestMapping(value="{name}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getShopInJSON(@PathVariable String name) {

		return new ResponseEntity<String>(name, HttpStatus.OK);

	}
	@RequestMapping(value="/v2/{name}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getShopInJSON2(@PathVariable String name) {

		return new ResponseEntity<String>(name + "_hola", HttpStatus.OK);

	}
	@RequestMapping(value="/v3/{name}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	public ResponseEntity<Shop> getShopInJSON3(@PathVariable String name) {
		Shop  s;
		s = new Shop();
		s.setName(name);
		s.setSize(10);
		return new ResponseEntity<Shop>(s, HttpStatus.OK);

	}
	@RequestMapping(value="/shop/create", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public ResponseEntity<Void> createUser(@RequestBody String shop){
		System.out.println("Creating Shop "+shop);
		
		return new ResponseEntity<Void>( HttpStatus.OK );
	}

}
