package org.example.ss.torneo.negocio;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.example.ss.torneo.persistencia.*;

@Path("/")
public class TorneoController {

	private static final int MAXNUMJUGADORES = 3;
	private ITorneoDAO torneo = new TorneoDAOImpl();
	
	// Recurso Jugador (POST, GET, PUT y DELETE)
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response creaJugador(String ne, Jugador j, @Context UriInfo uriInfo) {
		ResponseBuilder response;
		torneo.anadeJugador(ne, j);
		int dorsal = j.getDorsalJugador();
		if (dorsal != -1) {
			JugadorRepresentation newJugador = new JugadorRepresentation(
					torneo.getJugador(ne, dorsal), uriInfo);
			response = Response.created(uriInfo.getBaseUriBuilder().
					path(newJugador.getDorsalJugador()).build()).entity(newJugador);
		} else {
			response = Response.serverError();
		}
		return response.build();
	}

	//Hay que hacer el pathparam para categoria
	@GET
	@Path("/{categoria}/{nombreEquipo}/{dorsalJugador}")
	@Produces("application/xml")
	public Response getJugador(@PathParam("nombreEquipo") String ne, 
			@PathParam("dorsalJugador") int dorsal, @Context UriInfo uriInfo) {
		
		ResponseBuilder response;
		Jugador jugador = torneo.getJugador(ne, dorsal);		
		if (jugador != null) {
			JugadorRepresentation jugRep = new JugadorRepresentation(jugador, uriInfo);
			response = Response.ok(jugRep);
		} else {
			response = Response.status(Response.Status.NOT_FOUND);
		}
		return response.build();
	}
	
	//No estamos seguros de que los que hemos puesto queryparam lo sean
	//pero solo no puede haber 3 parametros sin anotar
	@PUT
    @Path("/{categoria}/{nombreEquipo}/{dorsalJugador}")
    @Produces("application/xml")
    public Response actualizaJugador (@PathParam("nombreEquipo")String ne, 
			@PathParam("dorsalJugador")int dorsal, @QueryParam("goles")int goles, 
			@QueryParam("tamarillas")int tAmarillas, @QueryParam("trojas")int tRojas,
			@Context UriInfo uriInfo) { 
		
		ResponseBuilder response;
		Jugador jugador = torneo.getJugador(ne, dorsal);
		if (jugador!=null) {
			torneo.actualizaJugador(ne, dorsal, goles, tAmarillas, tRojas);
			jugador = torneo.getJugador(ne, dorsal);
			JugadorRepresentation jugRep = new JugadorRepresentation(jugador, uriInfo);
			response = Response.ok(jugRep);
			
		} else {
			response = Response.status(Response.Status.NOT_FOUND);
		}
		return response.build();
		
	}
	
	@DELETE
    @Path("/{categoria}/{nombreEquipo}/{dorsalJugador}")
    @Produces("application/xml")
    public Response eliminaJugador (@PathParam("nombreEquipo") String ne, 
			@PathParam("dorsalJugador") int dorsal, @Context UriInfo uriInfo) { 
		ResponseBuilder response;
		Jugador jugador = torneo.getJugador(ne, dorsal);
		if (jugador!=null) {
			jugador = torneo.eliminaJugador(ne, dorsal);
			JugadorRepresentation jugRep = new JugadorRepresentation(jugador, uriInfo);
			response = Response.ok(jugRep);			
		} else {
			response = Response.status(Response.Status.NOT_FOUND);
		}
		return response.build();
	}

	// Recurso Categoria (GET)
	//Esta a medias, VAGO!
	@GET
	@Path("/{categoria}")
	@Produces("application/xml")
	public Response getEquipos(@PathParam("categoria") Categoria cat,
			@Context UriInfo uriInfo) {
		ResponseBuilder response;
		List<Equipo> equipos = torneo.getEquipos(cat);
		if (equipos.size()>= 0) {
			// Las peliculas las retornamos todas, porque sino necesitaríamos poner aquí también startIndex y endIndex
			CategoriaRepresentation catRep = new CategoriaRepresentation(equipos, uriInfo, null, null);
			response = Response.ok(catRep);
		} else {
			response = Response.status(Response.Status.NOT_FOUND);
		}
		return response.build();
	}
	
	// Recurso Equipo (GET)
	@GET
	@Path("/{categoria}/{nombreEquipo}")
	@Produces("application/xml")
	public Response getEquipo(@PathParam("nombreEquipo") String nombre, @Context UriInfo uriInfo) {
		ResponseBuilder response;		
		Equipo equipo = torneo.getEquipo(nombre);
		if (equipo != null) {
			EquipoRepresentation equRep = new EquipoRepresentation(equipo, uriInfo);
			response = Response.ok(equRep);
			System.out.println("Enviando la respueta");
		} else {
			response = Response.status(Response.Status.NOT_FOUND);
		}
		return response.build();
	}

	
	// Recurso Ranking (GET)
	@GET
	@Path("/ranking")
	@Produces("application/xml")
	public Response ranking(@Context UriInfo uriInfo,
			@QueryParam("categoria") String categoria,
			@QueryParam("startIndex") @DefaultValue("1") int startIndex,
			@QueryParam("endIndex") @DefaultValue("3") int endIndex) {
		
		ResponseBuilder response;

		if (startIndex < 1 || endIndex > torneo.movies()) {
			response = Response.status(Response.Status.BAD_REQUEST);
		}
		
		// Filtramos por índices
		jugadores = filterMovies(jugadores, startIndex, endIndex);		

	    if (jugadores.size() > 0) {
			UriBuilder uriBase = uriInfo.getBaseUriBuilder();
			UriBuilder builder;
			int size = endIndex - startIndex + 1;

			AtomLink previous = null;
			AtomLink next = null;

			// "previous" link
			if (startIndex > 1) {
				builder = uriInfo.getAbsolutePathBuilder();
				builder.queryParam("startIndex", Math.max(startIndex - MAXNUMJUGADORES, 0));
				builder.queryParam("endIndex", startIndex - 1);
				previous = new AtomLink("previous", builder.build().toString());
			}
			
			// "next" link
			if (endIndex < torneo.getMovies().size()) {
				builder = uriInfo.getAbsolutePathBuilder();
				builder.queryParam("startIndex", endIndex + 1);
				builder.queryParam("endIndex",
						Math.min(endIndex + MAXNUMJUGADORES, torneo.getMovies().size()));
				next = new AtomLink("next", builder.build().toString());
			}

			response = Response.ok(new RankingRepresentation(jugadores, uriInfo,
					next, previous));
		} else {
			// Si ninguna película corresponde a la petición
			// retornamos una respuesta de tipo No Content (código 204)
			response = Response.noContent();
		}
		
		return response.build();

	}

	
	private static List<Jugador> filterJugadores(List<Jugador> jugadores, Categoria
			cat) {
		// TODO
		return jugadores;
	}

	private static List<Jugador> filterMovies(List<Jugador> jugadores, int startIndex,
			int endIndex) {
		return jugadores.subList(startIndex-1, endIndex);
	}
}
