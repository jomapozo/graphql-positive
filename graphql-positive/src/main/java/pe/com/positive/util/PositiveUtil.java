
package pe.com.positive.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @Author: JomaPozo.
 * @Datecreation: 11 jun. 2017 20:16:10
 * @FileName: PositiveUtil.java
 * @AuthorCompany:
 * @version: 0.1
 * @Description: unused class
 */
public class PositiveUtil {

    @Deprecated
    public static Map<String, Object> toMap(JSONObject object) throws JSONException{
	  Map<String, Object> map = new HashMap<String, Object>();

	  Iterator<String> keysItr = object.keys();
	  while (keysItr.hasNext()) {
		String key = keysItr.next();
		Object value = object.get(key);

		if ( value instanceof JSONArray ) {
		    value = toList((JSONArray) value);
		}

		else if ( value instanceof JSONObject ) {
		    value = toMap((JSONObject) value);
		}
		map.put(key , value);
	  }
	  return map;
    }

    @Deprecated
    public static List<Object> toList(JSONArray array) throws JSONException{
	  List<Object> list = new ArrayList<Object>();
	  for (int i = 0;i < array.length();i++) {
		Object value = array.get(i);
		if ( value instanceof JSONArray ) {
		    value = toList((JSONArray) value);
		}

		else if ( value instanceof JSONObject ) {
		    value = toMap((JSONObject) value);
		}
		list.add(value);
	  }
	  return list;
    }
}
