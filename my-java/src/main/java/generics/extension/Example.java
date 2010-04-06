package generics.extension;

import generics.extension.model.backend.Describeable;
import generics.extension.model.backend.ImMother;
import generics.extension.model.backend.ImPerson;
import generics.extension.model.service.Mother;

public class Example {
	public static void main(String[] args) {
		Mother mother = new Mother();
		mother.setName("Ania");
		mother.setHusbandName("Bartek");

		// no annotation needed, return type and conversion class explicitly specified
		ImMother imMother = PersonConverter.convert(mother, ImMother.class);
		System.out.println(imMother.describe());

		ImPerson imPerson2 = PersonAnnotatedConverter.convert(mother);
		System.out.println(imPerson2.describe());

		ImMother imPerson22 = (ImMother) PersonAnnotatedConverter.convert(mother);
		System.out.println(imPerson22.describe());

		Describeable imMother3 = GenericAnnotatedConverter.convert(mother, ImPerson.class);
		System.out.println(imMother3.describe());

		ImPerson imMother33 = GenericAnnotatedConverter.convert(mother, ImPerson.class);
		System.out.println(imMother33.describe());

		ImMother imMother333 = (ImMother) GenericAnnotatedConverter.convert(mother, ImPerson.class);
		System.out.println(imMother333.describe());

		ImMother imMother3333 = (ImMother) GenericAnnotatedConverter.convert(mother);
		System.out.println(imMother3333.describe());

		// Converter3.convert(mother);
		// Describeable backendInstance = Converter3.convert(mother, Describeable.class);
		// System.out.println(backendInstance.describe());
	}
}
