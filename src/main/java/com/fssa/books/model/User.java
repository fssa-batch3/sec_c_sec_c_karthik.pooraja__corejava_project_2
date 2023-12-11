package com.fssa.books.model;

import java.time.LocalDate;

public class User {
	private int id;
	private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean status;
    private Role role;
    private Status transfer_status;
    
    /**
     * Constructs a new `Book` object with default values for all attributes.
     */
    public User() {

    }

    // Constructor
    public User(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public User(String name, String phoneNumber, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setRole(Role role) {
    	this.role=role;
    }
    public Role getRole() {
        return role;
    }
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status isTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(Status transfer_status) {
		this.transfer_status = transfer_status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + ", status=" + status + ", role=" + role + ", transfer_status=" + transfer_status + "]";
	}

   
}
