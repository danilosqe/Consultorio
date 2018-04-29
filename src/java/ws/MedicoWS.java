/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import controle.ControleMedicos;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import modelo.MedicoModel;

/**
 * REST Web Service
 *
 * @author Danilo
 */
@Path("Consultorio")
public class MedicoWS {


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public MedicoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.MedicoWS
     *
     * @return an instance of java.lang.String
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Medico/get/{med_id}")
    public String getMedico(@PathParam("med_id") int med_id) {
        MedicoModel m = new MedicoModel();
        m.setMed_id(med_id);

        ControleMedicos c = new ControleMedicos();
        m = c.buscaMedico(m);

        Gson g = new Gson();

        return g.toJson(m);


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Medico/list")
    public String getListMedico() {
        ControleMedicos c = new ControleMedicos();
        List<MedicoModel> listaMedico = new ArrayList();
        
        listaMedico = c.buscarTodosOsMedicos();
        
        Gson g = new Gson();
        return g.toJson(listaMedico);
        

    }

    /**
     * PUT method for updating or creating an instance of MedicoWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("Medico/alterar")
    public String putJson(String content) {
        Gson g = new Gson();
        MedicoModel m = (MedicoModel) g.fromJson(content, MedicoModel.class);
        ControleMedicos c = new ControleMedicos();
        return c.alterarMedico(m);
        
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("Medico/cadastrar")
    public String cadastrarMedico(String content){
        Gson g = new Gson();
        MedicoModel m = (MedicoModel) g.fromJson(content, MedicoModel.class);
        ControleMedicos c = new ControleMedicos();
        return c.CadastroMedico(m);
    }
    
    @DELETE
    @Path("Medico/excluir/{med_id}")
    public boolean exluirMedico(@PathParam("med_id") int med_id)
    {
        MedicoModel m = new MedicoModel();
        m.setMed_id(med_id);
        
        return true;
    }
}
