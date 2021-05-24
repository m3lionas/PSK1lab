package lt.vu.PSK1lab.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.PSK1lab.entities.Band;
import lt.vu.PSK1lab.entities.Festival;
import lt.vu.PSK1lab.persistence.FestivalsDAO;
import lt.vu.PSK1lab.rest.contracts.BandDto;
import lt.vu.PSK1lab.rest.contracts.FestivalDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/festivals")
public class FestivalController {

    @Inject
    @Setter
    @Getter
    private FestivalsDAO festivalsDAO;

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Festival festival = festivalsDAO.findOne(id);
        if (festival == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        FestivalDto festivalDto = new FestivalDto();
        festivalDto.setName(festival.getName());

        List<BandDto> list = new ArrayList<>();
        for (Band band : festival.getBands()) {
            BandDto bandDto = new BandDto();
            bandDto.setName(band.getName());
            list.add(bandDto);
        }
        festivalDto.setBands(list);

        return Response.ok(festivalDto).build();
    }

    @Path("/{id}/{name}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long festivalId,
            @PathParam("name") final String festivalName) {
        try {
            Festival existingFestival = festivalsDAO.findOne(festivalId);
            if (existingFestival == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingFestival.setName(festivalName);
            festivalsDAO.update(existingFestival);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{name}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @PathParam("name") final String festivalName) {
        Festival newFestival = new Festival();
        newFestival.setName(festivalName);
        festivalsDAO.persist(newFestival);
        return Response.ok(Response.Status.OK).build();
    }
}