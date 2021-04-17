/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package femcoworking.servidor.Controllers;


import femcoworking.servidor.Controllers.UsuariController;
import femcoworking.servidor.Exceptions.BadRequestException;
import femcoworking.servidor.Exceptions.OficinaNotFoundException;
import femcoworking.servidor.Exceptions.UsuariNotAllowedException;
import femcoworking.servidor.Models.Oficina;
import femcoworking.servidor.Models.OficinaVisualitzacio;
import femcoworking.servidor.Models.PeticioReservaOficina;
import femcoworking.servidor.Models.Reserva;
import femcoworking.servidor.Models.Rol;
import femcoworking.servidor.Models.Usuari;
import femcoworking.servidor.Persistence.OficinaRepository;
import femcoworking.servidor.Persistence.ReservaRepository;
import femcoworking.servidor.Persistence.UsuariRepository;
import femcoworking.servidor.Services.ControlAcces;
import femcoworking.servidor.Services.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fernando
 */
@RestController
public class ReservaController {
    private static final Logger log = LoggerFactory.getLogger(ReservaController.class.getName());
    private final UsuariRepository usuariRepository;
    private final OficinaRepository oficinaRepository;
    private final ReservaRepository reservaRepository;
    private final ControlAcces controlAcces;
    private final Mapper mapper;

    public ReservaController(
        UsuariRepository usuariRepository,
        OficinaRepository oficinaRepository, 
        ReservaRepository reservaRepository, 
        ControlAcces controlAcces, 
        Mapper mapper
    ) {
        this.usuariRepository = usuariRepository;
        this.oficinaRepository = oficinaRepository;
        this.reservaRepository = reservaRepository;
        this.controlAcces = controlAcces;
        this.mapper = mapper;
    }

    @PostMapping("/reservaoficina")
    public String reservaOficina(@RequestBody PeticioReservaOficina reservaOficina) {
          
        log.trace("Rebuda petició de reserva d'oficina");        
        
        Reserva reserva = reservaOficina.getReserva();
        InicialitzarCampsNovaReserva(
            reservaOficina.getReserva(),
            reservaOficina.getReserva().getIdUsuari().getIdUsuari(),
            reservaOficina.getReserva().getIdOficina().getIdOficina()
        );
        ValidarCampsNovaReserva(reservaOficina.getReserva());

        reservaRepository.save(reservaOficina.getReserva());

        log.info("Reserva efectuada amb l'identificador " + reservaOficina.getReserva().getIdReserva());

        return "Reserva efectuada amb l'identificador " + reservaOficina.getReserva().getIdReserva();    
    }
    
    @GetMapping("/reserves/{codiAcces}")
    public List<Reserva> llistarReserves(@PathVariable String codiAcces) {
        log.info("Petició de llistar reserves del codi " + codiAcces);
        
        String idUsuari = controlAcces.ValidarCodiAcces(codiAcces);
        Usuari usuari = usuariRepository.findByIdUsuari(idUsuari);
        
        List<Reserva> reserves = new ArrayList<>();
        
        if (usuari.getRol() == Rol.CLIENT) {
            reserves = reservaRepository.findAllByIdUsuari(usuari);
        } else {
            reserves = reservaRepository.findAll();
        }
        
        log.info("Retornada llista de reserves");
        return reserves;
    }

    private void ValidarCampsNovaReserva(Reserva novaReserva) {

        if (novaReserva == null)
        {
            throw new BadRequestException("Reserva no informada");
        }

        if (novaReserva.getDataIniciReserva() == null)
        {
            throw new BadRequestException("El camp data_inici_reserva és obligatori");
        }
        
        if (novaReserva.getDataFiReserva() == null)
        {
            throw new BadRequestException("El camp data_fi_reseva és obligatori");
        }
        
        if (novaReserva.getDataIniciReserva().compareTo(novaReserva.getDataFiReserva()) > 0) {
            throw new BadRequestException("El camp data_fi_reseva és anterior a data_inici_reserva");
        }
    }

    private void InicialitzarCampsNovaReserva(Reserva novaReserva, String idUsuari, String idOficina) {
        Usuari usuari = usuariRepository.findByIdUsuari(idUsuari);
        novaReserva.setIdUsuari(usuari);
        Oficina oficina = oficinaRepository.findByIdOficina (idOficina);
        novaReserva.setIdOficina (oficina);
        novaReserva.setIdReserva(UUID.randomUUID().toString());
    }
    
    
    private void ValidarUsuariClient(String idUsuari) {
        Usuari usuari = usuariRepository.findByIdUsuari(idUsuari);
        if (usuari.getRol() != Rol.CLIENT)
        {
            throw new UsuariNotAllowedException("Aquesta funcionalitat requereix el rol de client");
        }
    }
}

    

