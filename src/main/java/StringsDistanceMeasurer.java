

public class StringsDistanceMeasurer {
	private int shortestDist;
	private int maxH;
	private int maxV;

	public int distMeasurer(String a, String b) {
		if (a.length() < b.length()) {
			String c = a;
			a = b;
			b = c;
			c = null;
		}
		maxH = a.length()+1;
		maxV = b.length()+1;
		distMeasurer2(new StringBuilder(a), new StringBuilder(b),?,?, 0, 0, 0);
		return this.shortestDist;
	}

	private void distMeasurer2(StringBuilder a, StringBuilder b, int currentCharStringA, int currentCharStringB, int dist, int h, int v) {
		
		// bottom:
		boolean borderReached = v > maxV || h > maxH;
		boolean stringsEqual = a.toString().equals(b.toString());  
		
		if (borderReached || stringsEqual) {
			if (stringsEqual && this.shortestDist > dist) {
				this.shortestDist = dist;
			}
			return;
		}

		// recursive calls:
		//delete:
		distMeasurer2(a.deleteCharAt(?), b,?,?, dist++, h + 1, v);
		//insert:
		distMeasurer2(a.insert(?, b.charAt(?)), b,?,?, dist++, h, v + 1);

		if (a.charAt(currentCharStringA) == b.charAt(currentCharStringB)) {
			distMeasurer2(a, b, dist, h + 1, v + 1);
		} else {
			// swap
			a.setCharAt(currentCharStringA, b.charAt(currentCharStringB));
			distMeasurer2(a, b, dist++, h + 1, v + 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(new StringsDistanceMeasurer().distMeasurer("alab", "dnj"));
	}

}
