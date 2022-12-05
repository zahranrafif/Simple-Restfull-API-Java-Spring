package ws.b.project04;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Zahran Rafif Pc
 */

//mendefinisikan layanan web RESTful
@RestController
public class myController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product sedap = new Product();
        //set id 1
        sedap.setId("1");
        //set Nama
        sedap.setName("Mie Sedap[");
        productRepo.put(sedap.getId(), sedap);
      
        Product lemonilo = new Product();
        //set id 2
        lemonilo.setId("2");
        //set Nama
        lemonilo.setName("Lemonilo");
        productRepo.put(lemonilo.getId(), lemonilo);
        
        Product indomie = new Product();
        //set id 2
        indomie.setId("3");
        //set Nama
        indomie.setName("Indomie");
        productRepo.put(indomie.getId(), indomie);
    }
    
   //@RequestMapping menentukan URL /products/{id}
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   //@PathVariabel menentukan URI permintaan khusus atau dinamis
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
       //if jika id tidak ada maka akan muncul pesan  Product deleted not found dan data tidak didelete
       if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("Product deleted not found", HttpStatus.OK);
       }
       //else jika id ada maka muncul pesan Product is deleted successsfully dan data didelete
       else{
           productRepo.remove(id);
           return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
       }
      
   }
   
   //@RequestMapping menentukan URL /products/{id}
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   //@RequestBody menentukan jenis konten isi permintaan.
   //@PathVariabel menentukan URI permintaan khusus atau dinamis
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
       //if jika id tidak ada maka akan muncul pesan Product updated not found dan data tidak diubah
       if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("Product updated not found", HttpStatus.OK);
       }
       //else jika id ada maka muncul pesan Product is updated successsfully dan data diubah
       else{
           //menghapus berdasar id
           productRepo.remove(id);
           product.setId(id);
           //mengupdate data berdasar id
           productRepo.put(id, product);
           return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
       }
      
   }
   
   //@RequestMapping menentukan URL /products
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   //@RequestBody menentukan jenis konten isi permintaan.
   public ResponseEntity<Object> createProduct(@RequestBody @Validated Product product) {
       //if jika id sudah ada maka akan muncul pesan update not found dan data tidak ditambahkan
       if(productRepo.containsKey(product.getId())){
           return new ResponseEntity<>("Product not duplicate", HttpStatus.OK);
       }
       //else jika id tidak ada maka muncul pesan Product is updated successsfully dan data ditambahkan
       else{
           //mengupdate/membuat data berdasar id
           productRepo.put(product.getId(), product);
           return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
       }
   }
   //@RequestMapping menentukan URL /products
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
