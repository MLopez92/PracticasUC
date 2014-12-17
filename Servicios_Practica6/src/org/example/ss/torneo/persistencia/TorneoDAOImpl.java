package org.example.ss.torneo.persistencia;

import java.util.List;

public class TorneoDAOImpl implements ITorneoDAO{

	@Override
	public List<Equipo> getEquipos(Categoria cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nuevoEquipo(Categoria cat, Equipo e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Equipo getEquipo(String equipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jugador getJugador(String equipo, int dorsal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean anadeJugador(String equipo, Jugador j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Jugador eliminaJugador(String equipo, int dorsal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jugador actualizaJugador(String equipo, int dorsal, int goles,
			int tAmarillas, int tRojas) {
		// TODO Auto-generated method stub
		return null;
	}

}
