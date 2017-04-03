package com.cecwj.common;

import com.cecwj.model.Bed;
import com.cecwj.model.PCondition;
import com.cecwj.model.Patient;
import com.cecwj.model.Patient.Status;
import java.util.List;

public class Sort {
	public static List<PCondition> reverse(List<PCondition> list) {
		int size = list.size();
		for (int i = 0; i < size / 2; i++) {
			PCondition p = (PCondition) list.get(i);
			list.set(i, (PCondition) list.get(size - 1 - i));
			list.set(size - 1 - i, p);
		}
		return list;
	}

	public static List<Bed> orderByBStatus(List<Bed> list) {
		int flag = -1;
		Bed bed;
		do {
			flag++;
			bed = (Bed) list.get(flag);
		} while (

		bed.getStatus().equals(com.cecwj.model.Bed.Status.EMPTY));
		for (int i = flag + 1; i < list.size(); i++) {
			Bed temp = (Bed) list.get(i);
			if (temp.getStatus().equals(com.cecwj.model.Bed.Status.EMPTY)) {
				bed = (Bed) list.get(flag);
				list.set(flag, (Bed) list.get(i));
				list.set(i, bed);
				flag++;
			}
		}
		return list;
	}

	public static List<Patient> orderByPStatus(List<Patient> list) {
		int flag = -1;
		Patient patient;
		do {
			flag++;
			patient = (Patient) list.get(flag);
		} while (

		patient.getStatus().equals(Patient.Status.NOASSGNED));
		for (int i = flag + 1; i < list.size(); i++) {
			Patient temp = (Patient) list.get(i);
			if (temp.getStatus().equals(Patient.Status.NOASSGNED)) {
				patient = (Patient) list.get(flag);
				list.set(flag, (Patient) list.get(i));
				list.set(i, patient);
				flag++;
			}
		}
		return list;
	}
}
