package br.com.cincopatas.openapi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Token Controller")
public interface TokenOpenAPI {
	
	@ApiOperation(value="Revoke Token", httpMethod="")
	void revoke(HttpServletRequest req, HttpServletResponse resp);
	
}
