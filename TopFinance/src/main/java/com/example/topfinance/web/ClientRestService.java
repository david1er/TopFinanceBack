package com.example.topfinance.web;

import com.example.topfinance.dao.ClientRepository;
import com.example.topfinance.entities.Client;
import com.example.topfinance.entities.Pret;
import com.example.topfinance.metier.ClientMetier;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class ClientRestService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMetier clientMetier;

	@Value("${images.dir}")
	private String imageDir;

	List<String> files = new ArrayList<String>();

	@PostMapping("/clients")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
												   @RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {
		String message = "";

		files.add(file.getOriginalFilename());
		Client client = new ObjectMapper().readValue(user, Client.class);
		client.setPhoto(file.getOriginalFilename());
		Client dbPerson=clientRepository.save(client);

		if(dbPerson!=null) {
			client.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+client.getCode_client()));
			message = "Vous avez bien enregistrer " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			message = "Echec d'enregistrer " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping("/clients/noPhoto")
	public Client saveClientWithoutPhoto(@RequestBody Client clients) {
		return clientRepository.save(clients);
	}

	/*public ResponseEntity<String> saveClientWithoutPhoto(@RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {
		String message = "";

		files.add(file.getOriginalFilename());
		Client client = new ObjectMapper().readValue(user, Client.class);
		client.setPhoto(file.getOriginalFilename());
		Client dbPerson=clientRepository.save(client);

		if(dbPerson!=null) {
			client.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+client.getCode_client()));
			message = "Vous avez bien enregistrer " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			message = "Echec d'enregistrer " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}*/

	@RequestMapping(value="/clientPhoto/{id}",method=RequestMethod.GET,
			produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getFichier(@PathVariable Long id) throws Exception {
		File f=new File(imageDir+id);
		//return IOUtils.toByteArray(new FileInputStream(f));
		return null; //pour test
	}

	@RequestMapping(value="/clients", method=RequestMethod.GET)
	public List<Client> listClient() {

		return clientMetier.listClient();
	}

	@RequestMapping(value="clients/{code_client}", method=RequestMethod.GET)
	public Optional<Client> getClient(@PathVariable Long code_client) {
		return clientRepository.findById(code_client);
	}

	@RequestMapping(value="clients/{code_client}", method=RequestMethod.DELETE)
	public void deleteClient(@PathVariable Long code_client) {
		clientRepository.deleteById(code_client);
	}

	@RequestMapping(value="clients/{code_client}", method=RequestMethod.PUT)
	public Client editClient(@RequestBody Client m, @PathVariable Long code_client) {
		m.setCode_client(code_client);
		return clientRepository.save(m);
	}

}
