package com.example.demo.repository.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	

	@Id
	@Column(name="usua_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="usua_id_seq" )
	@SequenceGenerator(name="usua_id_seq", sequenceName = "usua_id_seq", allocationSize =1 )
	private Integer id;
	
	@Column(name="usua_nombre")
	private String nombre;
	
	@Column(name="usua_password")
	private String password;

	//SET GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}
