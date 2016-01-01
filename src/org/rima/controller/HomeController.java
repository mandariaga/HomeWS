package org.rima.controller;


import java.util.List;

import org.rima.db.DbConnection;
import org.rima.model.Producto;
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
	@RequestMapping(value="/v4", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getShopInJSON4() {
		DbConnection db;
		db = new DbConnection();
		db.getConnection();
		List<Producto> productos = db.select();
		String prod;
		prod = new String();
		int i = 0; 
		int k = 0;
		for(Producto p : productos){
			System.out.println(p.getCodigoProducto());
			System.out.println(p.getNombreProducto());
			System.out.println(p.getTipo());
			System.out.println(p.getDescripcion());
			System.out.println(p.getPrecio());
			prod = prod + p.getNombreProducto()+ p.getDescripcion() + "\n";
			
		}
		
		return new ResponseEntity<String>(prod, HttpStatus.OK);

	}
	@RequestMapping(value="/v5", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> insertProducto() {
		DbConnection db;
		db = new DbConnection();
		db.getConnection();
		Producto p; 
		p = new Producto();
		p.setCodigoProducto(34);
		p.setNombreProducto("PALA");
		p.setTipo("A");
		p.setDescripcion("HERRAMIENTAS");
		p.setPrecio(3000F);
		db.insert(p);
		
		return new ResponseEntity<String>(HttpStatus.OK);

	}
	@RequestMapping(value="/v7", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getShopInJSON7() {
		DbConnection db;
		db = new DbConnection();
		db.getConnection();
		List<Producto> productos = db.select();
		String prod;
		prod = new String();
		
		for(Producto p : productos){
			System.out.println(p.getCodigoProducto());
			System.out.println(p.getNombreProducto());
			System.out.println(p.getTipo());
			System.out.println(p.getDescripcion());
			System.out.println(p.getPrecio());
			if (p.getPrecio() > 1000);
			prod = prod + p.getNombreProducto()+ p.getDescripcion() + p.getTipo() + p.getPrecio();
			
		}
		
		return new ResponseEntity<String>(prod, HttpStatus.OK);
	}
}
