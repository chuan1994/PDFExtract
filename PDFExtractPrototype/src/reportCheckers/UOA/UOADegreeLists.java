package reportCheckers.UOA;

import java.util.ArrayList;

/**
 * Storage class which may help with searching later if full list can be devised
 * @author cwu323
 *
 */
public class UOADegreeLists {
	private ArrayList<String> mastersDegrees = new ArrayList<String>();
	private ArrayList<String> doctoralDegrees = new ArrayList<String>();
	
	public UOADegreeLists(){
		addMasters();
		addDoctors();
	}
	
	public ArrayList<String> getMasterDegrees(){
		return mastersDegrees;
	}
	
	public ArrayList<String> getDoctoralDegrees(){
		return doctoralDegrees;
	}
	
	public ArrayList<String> getAllDegrees(){
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(mastersDegrees);
		temp.addAll(doctoralDegrees);
		
		return temp;
	}
	
	
	private void addMasters(){
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
		mastersDegrees.add("");
	}
	private void addDoctors(){
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		doctoralDegrees.add("");
		
	}
	
}
