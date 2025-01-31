package pe.gob.pj.conexiones.infraestructure.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.pj.conexiones.domain.model.Aplicativo;
import pe.gob.pj.conexiones.infraestructure.common.utils.ProjectConstants;
import pe.gob.pj.conexiones.infraestructure.rest.responses.AplicativoResponse;

@RestController
public class DefaultController implements Default{
    @Override
    @GetMapping(value = "/healthcheck")
    public ResponseEntity<AplicativoResponse> healthcheck( String formatoRespuesta) {
        var res = new AplicativoResponse();

        res.setCodigoOperacion("546565416516");
        var healthcheck = new Aplicativo(ProjectConstants.Aplicativo.NOMBRE,"Disponible",ProjectConstants.Aplicativo.VERSION);

        res.setData(healthcheck);

        return new ResponseEntity<>(res, getHttpHeader(formatoRespuesta), HttpStatus.OK);
    }
}
