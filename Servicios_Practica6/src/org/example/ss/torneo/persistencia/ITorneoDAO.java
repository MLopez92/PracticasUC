package org.example.ss.torneo.persistencia;

import java.util.List;

public interface ITorneoDAO {
	
	/**
	 * Retorna la lista de equipos de una categoria
	 * @param cat
	 * @return Lista de equipos de la categoria
	 */
	public List<Equipo> getEquipos(Categoria cat);
	
	/**
	 * Anade un nuevo equipo a una categoria
	 * @param cat
	 * @param e
	 * @return true si el equipo se crea
	 *         false si no se puede crear (excede el numero maximo de equipos)
	 */
	public boolean nuevoEquipo(Categoria cat, Equipo e);
	/**
	 * Retorna el equipo cuyo nombre se pasa como par�metro
	 * @param equipo
	 * @return El equipo o null si no existe
	 */
	public Equipo getEquipo(String equipo);
	
	/**
	 * Retorna el jugador del equipo y dorsal indicados
	 * @param equipo
	 * @param dorsal
	 * @return El jugador o null si no existe
	 */
	public Jugador getJugador(String equipo, int dorsal);
	
	/**
	 * Anade un nuevo jugador a un equipo
	 * @param equipo 
	 * @param j
	 * @return true si se anade el jugador 
	 *         false si no se puede anadir (excede el numero maximo de jugadores)
	 */
	public boolean anadeJugador(String equipo, Jugador j);
	
	/**
	 * Elimina el jugador con el dorsal indicado del equipo
	 * @param equipo
	 * @param dorsal
	 * @return El jugador eliminado o null si no existe
	 */
	public Jugador eliminaJugador(String equipo, int dorsal);
	
	/**
	 * Actualiza el n�mero de goles y tarjetas de un jugador
	 * @param equipo
	 * @param dorsal
	 * @param goles
	 * @param tAmarillas
	 * @param tRojas
	 * @return EL jugador actualizado, null si no se encontro
	 */
	public Jugador actualizaJugador(String equipo, int dorsal, int goles, int tAmarillas, int tRojas);
	

}
