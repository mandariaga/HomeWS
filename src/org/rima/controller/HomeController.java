package org.rima.controller;

import org.rima.model.Shop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
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

}
