package backend;

import game.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaScannerConnection;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Storage {

	private static final String OBJECT_KEY = "cricket_scoring";
	private static SharedPreferences prefs;
	private static Gson gson = new Gson();
	private static JsonParser jparser = new JsonParser();

	public static void write(Game game, Activity activity) {
		SharedPreferences prefs = activity.getSharedPreferences(null, Context.MODE_PRIVATE);
		Editor edit = prefs.edit();
		edit.putString(OBJECT_KEY, gson.toJson(game));
		edit.commit();
	}

	public static void clear(Activity activity) {
		prefs = activity.getSharedPreferences(null, Context.MODE_PRIVATE);
		Editor edit = prefs.edit();
		edit.clear();
		edit.commit();
	}

	public static Game getGame(Activity activity) {
		prefs = activity.getSharedPreferences(null, Context.MODE_PRIVATE);

		String gameAsString = prefs.getString(OBJECT_KEY, null);

		if (gameAsString != null) {
			return gson.fromJson(jparser.parse(gameAsString).getAsJsonObject(), Game.class);
		} else {
			return new Game();
		}
	}
	
	public static boolean doesFileExist(Activity activity) {
		prefs = activity.getSharedPreferences(null, Context.MODE_PRIVATE);
		return prefs.getString(OBJECT_KEY, null) == null ? false : true;
	}
	
	public static String getFileAsString(String fileName) throws Exception {
		File file = new File(Environment.getExternalStorageDirectory(), fileName);
		FileInputStream is = new FileInputStream(file);
		
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line).append("\n");
	    }
	    reader.close();
	    is.close();
	    return sb.toString();
	}
	
	//TODO do not restrict to External Storage
	public static void writeToXml(Game game, String fileName, Context context) {
		File file = new File(Environment.getExternalStorageDirectory(), fileName);
		
		XMLInteraction xmlInteraction = new XMLInteraction();
		String xml = xmlInteraction.writeXML(game);
		
		try {
			FileOutputStream outputStream2 = new FileOutputStream(file);
			outputStream2.write(xml.getBytes());
			outputStream2.close();
			
			//needed to force a scan so that a file placed here can be visible via MTP without a reboot (USB bug)
			MediaScannerConnection.scanFile(context, new String[] { file.getAbsolutePath() }, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
