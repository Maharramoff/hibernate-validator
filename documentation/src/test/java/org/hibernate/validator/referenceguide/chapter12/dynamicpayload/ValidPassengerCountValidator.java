/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
//spotless:off
//tag::include[]
package org.hibernate.validator.referenceguide.chapter12.dynamicpayload;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;

//end::include[]
import java.util.Map;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

//spotless:on
//tag::include[]
public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {

	private static final Map<Integer, String> suggestedCars = newHashMap();

	static {
		suggestedCars.put( 2, "Chevrolet Corvette" );
		suggestedCars.put( 3, "Toyota Volta" );
		suggestedCars.put( 4, "Maserati GranCabrio" );
		suggestedCars.put( 5, " Mercedes-Benz E-Class" );
	}

	@Override
	public void initialize(ValidPassengerCount constraintAnnotation) {
	}

	@Override
	public boolean isValid(Car car, ConstraintValidatorContext context) {
		if ( car == null ) {
			return true;
		}

		int passengerCount = car.getPassengers().size();
		if ( car.getSeatCount() >= passengerCount ) {
			return true;
		}
		else {

			if ( suggestedCars.containsKey( passengerCount ) ) {
				HibernateConstraintValidatorContext hibernateContext = context.unwrap(
						HibernateConstraintValidatorContext.class
				);
				hibernateContext.withDynamicPayload( suggestedCars.get( passengerCount ) );
			}
			return false;
		}
	}
}
//end::include[]
