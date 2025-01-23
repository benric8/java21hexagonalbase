package pe.gob.pj.conexiones.infraestructure.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pj.conexiones.infraestructure.rest.responses.AplicativoResponse;


@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Tag(name = "preestablecido", description = "Api para manejar endpoints Bases")
public interface Default extends Base{
    /**
     * Método que sirve para verificar versión actual del aplicativo
     *
     * @param cuo Código único de log
     * @return Datos del aplicativo
     */

    @Operation(summary = "Verifica Situación", description = "Permite verificar estado del servicio")
    @ApiResponse(responseCode = "200", description = "Verificación exitosa",
            content = @Content(schema = @Schema(implementation = AplicativoResponse.class)))
    public ResponseEntity<AplicativoResponse> healthcheck(

            @RequestParam(defaultValue = "json", required = false) String formatoRespuesta);



}
