package example;

import example.dto.*;
import example.dto.constants.*;
import example.dto.interfaces.*;

import org.hibernate.Session;

public class Main {

	private static <T extends IJoined> void together(T tbl)
	{
		try {
			int i = 1;
			for (BREED breed : BREED.values()){
				int j = 1;
				for (COLOR color : COLOR.values())
					tbl.insert(i, j++);
				i++;
			}
		} catch (Exception e) {}

		tbl.selectAll();
	}
	private static <T extends ILookup, U extends Enum<U>> void lookup(T tbl, Class<U> data)
	{
			try {
				for (Enum<U> unit: data.getEnumConstants())
					tbl.insert(unit.name());
			} catch (Exception e) {
				return ;
			}

			tbl.selectAll();
	}

	public static void main(String[] args) {
		org.hibernate.Session session = HibernateUtils.getSessionFactory().openSession();
		lookup(new Breed(session), BREED.class);
		lookup(new Color(session), COLOR.class);
		together(new Dog(session));
		together(new DogExpanded(session));
		HibernateUtils.shutdown();
	}
}
