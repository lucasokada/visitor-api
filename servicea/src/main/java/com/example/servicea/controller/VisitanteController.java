import com.example.servicea.VisitanteModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class VisitanteController {

    @PostMapping
    public String criarVisitante(@RequestBody VisitanteModel visitante) {
        return "Visitante criado com sucesso!";
    }
}