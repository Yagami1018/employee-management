package cu.uho.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
	//attb
	private String fullName;
	private final String CI;
	private String sex;
	private String address;
	List<Double>Evaluaciones;

	public Employee(String fullName,String CI,String sex){
		setFullName(fullName);
		//validacion del CI
		if(CI != null && CI.matches("\\d{11}")) { this.CI = CI;}
		else { throw new IllegalArgumentException("Solo puede contener numeros y tener 11 digitos");}
		setSex(sex);
	}
	
	public Employee(String fullName,String CI,String sex,String address) {
		this(fullName, CI, sex);
		setAddress(address);
		Evaluaciones = new ArrayList();
	}
	
	public void setFullName(String fullName){
		if(fullName == null) {throw new NullPointerException();}
		if(fullName.trim().isEmpty()) {throw new IllegalArgumentException("El nombre no puede estar vacio");}
		if(fullName.matches(".*\\d.*")) {throw new IllegalArgumentException("no puede contener numeros");}
		this.fullName = fullName;
	}
	public String getFullName() {return fullName;}
	
	public void setSex(String sex) {
		if(sex == null) {throw new NullPointerException();}
		
		if(!sex.matches("[MF]"))throw new IllegalArgumentException();
		this.sex = sex;
	}
	public String getSex() {return sex;}
	
	public void setAddress(String address) {
		if(address == null) { throw new NullPointerException();}
		
		if(address.trim().isEmpty()) {throw new IllegalArgumentException(address);}
		
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	
	public void agregarEvaluaciones(Double evaluacion) {
		Evaluaciones.add(evaluacion);
	}
	public Double evaluacionGeneral() {
		Double resultado = 0.0;
		for (Double eva : Evaluaciones) {
			resultado += eva;
		}
		return resultado /= Evaluaciones.size();
	
	}
	
	
	public String getCI() {return CI;}
	@Override
	public String toString() {
		return "Full Name: " + getFullName() + "\nCarnet: " + getCI() + "\nSexo: "
				+ getSex() + "\nDirecion: " + getAddress() + "\nEvaluacion General: "+evaluacionGeneral();
	}
}


