package com.framework.db.sqlserver;

import java.io.Serializable;

public class Config implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String port ;
	private String DBAddress ;
	private String DBPort ;
	private String DBUser ;
	private String DBPassword ;
	private String DBDatabase ;
	private String DBDriver ;
	private String DBDatabaseName ;
	private String standPushPort ;
	private String positionPushPort ;
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDBAddress() {
		return DBAddress;
	}
	public void setDBAddress(String dBAddress) {
		DBAddress = dBAddress;
	}
	public String getDBPort() {
		return DBPort;
	}
	public void setDBPort(String dBPort) {
		DBPort = dBPort;
	}
	public String getDBUser() {
		return DBUser;
	}
	public void setDBUser(String dBUser) {
		DBUser = dBUser;
	}
	public String getDBPassword() {
		return DBPassword;
	}
	public void setDBPassword(String dBPassword) {
		DBPassword = dBPassword;
	}
	public String getDBDatabase() {
		return DBDatabase;
	}
	public void setDBDatabase(String dBDatabase) {
		DBDatabase = dBDatabase;
	}
	public String getDBDriver() {
		return DBDriver;
	}
	public void setDBDriver(String dBDriver) {
		DBDriver = dBDriver;
	}
	public String getDBDatabaseName() {
		return DBDatabaseName;
	}
	public void setDBDatabaseName(String dBDatabaseName) {
		DBDatabaseName = dBDatabaseName;
	}
	public String getStandPushPort() {
		return standPushPort;
	}
	public void setStandPushPort(String standPushPort) {
		this.standPushPort = standPushPort;
	}
	public String getPositionPushPort() {
		return positionPushPort;
	}
	public void setPositionPushPort(String positionPushPort) {
		this.positionPushPort = positionPushPort;
	}
	

	
}
