package util;

public class ConvUtil {

	
	public static int asInt(String str) {
		if (str == null || str.isEmpty()) {
			return 0;
		}
		else {
			try {
				return Integer.parseInt(str);
			}
			catch (Exception e) {
			}
			return 0;
		}
	}
}
