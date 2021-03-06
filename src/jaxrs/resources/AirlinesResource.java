/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package jaxrs.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.tags.Tag;
import io.swagger.oas.annotations.tags.Tags;
import jaxrs.app.JAXRSApp;
import jaxrs.model.Airline;

@Path("")
@Schema(name = "Airline Booking API")
@Tags(tags = @Tag(name = "airline", description = "all the airlines methods"))
	public class AirlinesResource {
	private static Map<Integer, Airline> airlines = new ConcurrentHashMap<Integer, Airline>();
	
	static {
		airlines.put(1, new Airline("Acme Air", "1-888-1234-567"));
		airlines.put(2, new Airline("Acme Air Partner", "1-855-1284-563"));
		airlines.put(3, new Airline("PanAm 5000", "1-855-1267-561"));
	}
	
	public static Airline getRandomAirline(){
		return airlines.get(JAXRSApp.getRandomNumber(2,1));
	}
	
	@GET
	@Operation(
			method = "get",
			summary = "Retrieve all available airlines",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "successful operation",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(
											type = "array",
											implementation = Airline.class
											)
									)
							),
					@ApiResponse(
							responseCode = "404",
							description = "No airlines found",
									content = @Content(
											mediaType = "n/a"
											)
							)
			})
	@Produces("application/json")
	public Response getAirlines(){
		return Response.ok().entity(airlines.values()).build();
	}
	
}
