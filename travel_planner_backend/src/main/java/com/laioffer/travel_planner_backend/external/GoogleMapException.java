package com.laioffer.travel_planner_backend.external;

/**
 * An exception class for indicating failure of getting data from GoogleMap API.
 */
public class GoogleMapException extends RuntimeException {
		public GoogleMapException(String errorMessage) {
				super(errorMessage);
		}
}
