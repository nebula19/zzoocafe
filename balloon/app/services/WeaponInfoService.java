package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ebeans.Weapon;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.util.StringUtils;

import util.GsonUtil;

public class WeaponInfoService {

	
	final static String weaponInfoUrl= "https://docs.google.com/spreadsheet/pub?key=0AtYpK6T9q-Q-dGZwd0NOZ0E5clA0SGZXTDlraEV1RGc&single=true&gid=1&output=csv";
	
	public static boolean updateWeaponInfoDB() {
		
		HttpClient client = new DefaultHttpClient();
		
		HttpGet httpGet = new HttpGet(weaponInfoUrl);
		List<Weapon> weaponInfoList = new ArrayList<Weapon>();
		try {
			HttpResponse response = client.execute(httpGet);
			
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new  BufferedReader(new InputStreamReader(is));
			
			
			String header[] = null;
			String tmp;
			
			while (!StringUtils.isEmpty(tmp = br.readLine())) {
				header = tmp.split(",");
				break;
			}
			
			while ((tmp = br.readLine()) != null) {
				System.out.println(tmp);
				Weapon weapon = csvToWeapon(header, tmp);
				if (weapon != null) {
					weaponInfoList.add(weapon);
				}
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("weaponInfoList : " + weaponInfoList);
		System.out.println("weaponInfoList size : " + weaponInfoList.size());
//		System.out.println("weaponInfoList gson : " + GsonUtil.getGson().toJson(weaponInfoList));
		
		Weapon.replaceAll(weaponInfoList);
		
		
		return true;
	}

	private static Weapon csvToWeapon(String[] header, String tmp) {
		
		if (StringUtils.isEmpty(header)  || StringUtils.isEmpty(tmp)) {
			return null;
		}
		
		Weapon weapon = new Weapon();
		
		String values[] = tmp.split(",");
		int size = header.length;
		for (int i=0; i < size; i++) {
			
			if (header[i].equals("id")) {
				weapon.id = Long.parseLong(values[i]);
			}
			else if (header[i].equals("type")) {
				weapon.type = values[i].trim();
			}
			else if (header[i].equals("grade")) {
				weapon.grade = Integer.parseInt(values[i]);
			}
			else if (header[i].equals("name")) {
				weapon.name = values[i].trim();
			}
			else if (header[i].equals("damage")) {
				weapon.power = Float.parseFloat(values[i]);
			}
			else if (header[i].equals("shooting_rate")) {
				weapon.shootingRate = Float.parseFloat(values[i]);
			}
			else if (header[i].equals("critical_rate")) {
				weapon.criticalRate = Float.parseFloat(values[i]);
			}
			else if (header[i].equals("velocity")) {
				weapon.velocity = Float.parseFloat(values[i]);
			}
			else if (header[i].equals("image")) {
				weapon.image = values[i].trim();
			}
			else if (header[i].equals("sound")) {
				weapon.sound = values[i].trim();
			}
			else if (header[i].equals("price")) {
				weapon.price = Integer.parseInt(values[i]);
			}
		}
		
		return weapon;
	}
	
	
	
}
