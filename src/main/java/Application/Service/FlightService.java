package Application.Service;

import Application.Model.Flight;
import Application.DAO.FlightDAO;

import java.util.List;

/**
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 */
public class FlightService {
    FlightDAO flightDAO;

    /**
     * No-args constructor for a flightService instantiates a plain flightDAO.
     * There is no need to modify this constructor.
     */
    public FlightService(){
        flightDAO = new FlightDAO();
    }

    /**
     * Constructor for a flightService when a flightDAO is provided.
     * This is used for when a mock flightDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of FlightService independently of FlightDAO.
     * There is no need to modify this constructor.
     * @param flightDAO
     */
    public FlightService(FlightDAO flightDAO){
        this.flightDAO = flightDAO;
    }

    /**
     * TODO: Use the FlightDAO to add a new flight to the database.
     *
     * This method should also return the added flight. A distinction should be made between *transient* and
     * *persisted* objects - the *transient* flight Object given as the parameter will not contain the flight's id,
     * because it is not yet a database record. When this method is used, it should return the full persisted flight,
     * which will contain the flight's id. This way, any part of the application that uses this method has
     * all information about the new flight, because knowing the new flight's ID is necessary. This means that the
     * method should return the Flight returned by the flightDAO's insertFlight method, and not the flight provided by
     * the parameter 'flight'.
     *
     * @param flight an object representing a new Flight.
     * @return the newly added flight if the add operation was successful, including the flight_id. We do this to
     *         inform our provide the front-end client with information about the added Flight.
     */
    public Flight addFlight(Flight flight){
        return flightDAO.insertFlight(flight);
    }

    
    public Flight updateFlight(int flight_id, Flight flight){
        //FlightDAO c=new FlightDAO();
        if(flightDAO.getFlightById(flight_id)!=null){
            flightDAO.updateFlight(flight_id,flight);
            return new Flight(flight_id,flight.getDeparture_city(),flight.getArrival_city());
        }
        else {
            return null;
        }
    }

    public List<Flight> getAllFlights() {
        //FlightDAO b=new FlightDAO();
        return flightDAO.getAllFlights();
    }

    /**
     * TODO: Use the FlightDAO to retrieve a List containing all flights departing from a certain city and arriving at
     * some other city. You could use the flightDAO.getAllFlightsFromCityToCity method.
     *
     * @param departure_city the departing city of the flight.
     * @param arrival_city the arriving city of the flight.
     * @return all flights departing from departure_city and arriving at arrival_city.
     */
    public List<Flight> getAllFlightsFromCityToCity(String departure_city, String arrival_city) {
        //FlightDAO a=new FlightDAO();
        return flightDAO.getAllFlightsFromCityToCity(departure_city,arrival_city);
    }
}
