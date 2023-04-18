package com.microservices.microservice1.jwt;

public class SecurityConstants {
	public static final String JWT_SECRET="vlasecret";
	public static final long JWT_EXPIRATION=15*1000*60;//15 minutes

	public static final String ADMIN_SECRET="adminsecret";
	public static final String SELLER_SECRET="sellersecret";
}
