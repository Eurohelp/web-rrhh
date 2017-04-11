package stardog;

import java.util.ArrayList;

import code.GeneradorIndex;

public class main {
	public static void main(String[] args) {
		GeneradorIndex gi = new GeneradorIndex();
		Stardog st = new Stardog();
		ArrayList<ArrayList<String>> e = st.getPageData();
		System.out.println(gi.generarIndex(e));
	}
}
