package org.hibernate.validator.referenceguide.chapter12.constraintapi;

import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

public class RentalCarGroupSequenceProvider implements DefaultGroupSequenceProvider<RentalCar> {
	@Override
	public List<Class<?>> getValidationGroups(Class<?> klass, RentalCar car) {
		return null;
	}
}
