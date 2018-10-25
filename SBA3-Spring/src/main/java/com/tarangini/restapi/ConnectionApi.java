package com.tarangini.restapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarangini.model.Connection;
import com.tarangini.service.ConnectionService;

@RestController
@CrossOrigin
@RequestMapping("/connections")
public class ConnectionApi {
	@Autowired
	private ConnectionService conService;

	@GetMapping
	public ResponseEntity<List<Connection>> listConnectionsAction() {
		ResponseEntity<List<Connection>> resp = null;
		List<Connection> connections = conService.getAllConnections();
		if (connections != null && connections.size() > 0)
			resp = new ResponseEntity<List<Connection>>(connections, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Connection>>(HttpStatus.NOT_FOUND);
		return resp;
	}

	@GetMapping("/{name}")
	public ResponseEntity<Connection> getConnectionAction(@PathVariable("name") String name) {
		ResponseEntity<Connection> resp = null;
		Connection customer = conService.getConnectionByName(name);
		if (customer == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(customer, HttpStatus.OK);
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Connection> addConnectionAction(@RequestBody Connection customer) {
		ResponseEntity<Connection> resp = null;
		LocalDate date = LocalDate.now().plusDays(4);
		if(date.getDayOfWeek().getValue()<3)
			date = date.plusDays(1);
		System.out.println(date);
		customer.setDor(date);
		
		if (customer != null && !conService.exists(customer.getCid())) {
			conService.addConnection(customer);
			resp = new ResponseEntity<Connection>(customer, HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Connection>(HttpStatus.CONFLICT);

		return resp;
	}

	@PutMapping
	public ResponseEntity<Connection> updateConnectionAction(@RequestBody Connection customer) {
		ResponseEntity<Connection> resp = null;

		if (customer != null && conService.exists(customer.getCid())) {
			conService.updateConnection(customer);
			resp = new ResponseEntity<>(customer, HttpStatus.OK);
		}
		else 
			resp = new ResponseEntity<Connection>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Void> deleteConnectionAction(@PathVariable("cid") int cid) {
		ResponseEntity<Void> resp = null;
		if (conService.exists(cid)) {
			conService.removeConnection(cid);
			resp = new ResponseEntity<>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
}
