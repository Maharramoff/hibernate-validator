/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.validator.referenceguide.chapter09;

import static org.junit.Assert.assertTrue;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Positive;

import org.hibernate.validator.HibernateValidator;

import org.junit.Test;

public class LoggingConfigurationTest {

	@Test
	public void programmaticConfiguration() throws Exception {
		//tag::programmatic[]
		Validator validator = Validation.byProvider( HibernateValidator.class )
				.configure()
				.showValidatedValuesInTraceLogs( true )
				.buildValidatorFactory()
				.getValidator();
		//end::programmatic[]
		assertTrue( validator.validate( new Foo( 1 ) ).isEmpty() );
	}

	@Test
	public void programmaticConfigurationProperty() throws Exception {
		//tag::programmaticProperty[]
		Validator validator = Validation.byProvider( HibernateValidator.class )
				.configure()
				.addProperty( "hibernate.validator.show_validated_value_in_trace_logs", "true" )
				.buildValidatorFactory()
				.getValidator();
		//end::programmaticProperty[]
		assertTrue( validator.validate( new Foo( 1 ) ).isEmpty() );
	}


	public class Foo {

		@Positive
		private final int value;

		private Foo(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
