package com.example.demo1;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import org.springframework.ui.ModelMap;



@RestController
@RequestMapping("/api")
public class TutoController {
	
	


   @Autowired
   RestTemplate restTemplate;

 
	
	
	

    @Autowired
	private TutoRepository tutoRepository;

    @CrossOrigin(allowedHeaders = {"Authorization", "Origin"})

	@GetMapping("/Tutos")
	public ResponseEntity<List<Tuto>> getAllTutos(@RequestParam(required = false) String title) {
		try {
			List<Tuto> Tutos = new ArrayList<Tuto>();

			if (title == null)
				tutoRepository.findAll().forEach(Tutos::add);
			else
				tutoRepository.findByTitleContaining(title).forEach(Tutos::add);

			if (Tutos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Tutos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Tutos/{id}")
	public ResponseEntity<Tuto> getTutoById(@PathVariable("id") long id) {
		Optional<Tuto> TutoData = tutoRepository.findById(id);

		if (TutoData.isPresent()) {
			return new ResponseEntity<>(TutoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/Tutos")
	public ResponseEntity<Tuto> createTuto(@RequestBody Tuto tuto) {
		try {
			Tuto _Tuto = tutoRepository
					.save(new Tuto(tuto.getTitle(), tuto.getDescription()));
			return new ResponseEntity<>(_Tuto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Tutos/{id}")
	public ResponseEntity<Tuto> updateTuto(@PathVariable("id") long id, @RequestBody Tuto Tuto) {
		Optional<Tuto> TutoData = tutoRepository.findById(id);

		if (TutoData.isPresent()) {
			Tuto _Tuto = TutoData.get();
			_Tuto.setTitle(Tuto.getTitle());
			_Tuto.setDescription(Tuto.getDescription());
			
			return new ResponseEntity<>(tutoRepository.save(_Tuto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Tutos/{id}")
	public ResponseEntity<HttpStatus> deleteTuto(@PathVariable("id") long id) {
		try {
			tutoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Tutos")
	public ResponseEntity<HttpStatus> deleteAllTutos() {
		try {
			tutoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
    
    @GetMapping("/login")
    public String redirectTo(){
        return "/";
    }
    
    
   
     
       @GetMapping(value="/error")
       public String hello(ModelMap Model) {
           return "list";
       }
       
       @GetMapping("/{symbol}")
       public String get(@PathVariable String symbol){
           return "heyyyy";
       }


}
