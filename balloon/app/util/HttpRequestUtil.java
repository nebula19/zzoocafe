package util;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

	
	public static int getIntParamter(HttpServletRequest request, String key) {
		String param = request.getParameter(key);
		if (param == null || param.isEmpty()) {
			return 0;
		}
		else {
			try {
				return Integer.parseInt(param);
			}
			catch (Exception e) {
			}
			return 0;
		}
	}
}
