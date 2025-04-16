package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class LlamadasIA {
	private static LlamadasIA miIA = new LlamadasIA();
	
	private LlamadasIA() {
	}
	
	public static LlamadasIA getLlamadasIA() {
		return miIA;
	}
	
	public String movimientoIA(int persY, int persX, int bossY, int bossX) {
		try {
		String modelName = "llama3.1";
		String promptText = "You are playing as a boss character on a 2D grid (board size: 16 tiles wide and 10 tiles tall)."
				+ "The coordinate origin (0,0) is at the bottom-left corner."
				+ "The x-axis increases to the right (0 to 15)."
				+ "The y-axis increases upward (0 to 10)."
				+ "The player is standing still at position (x= "+persX+ ", y= "+persY+")."
				+ "You (the boss) are currently at position (x= "+bossX+ ", y= "+bossY+")."
				+ "Your objective is to move one tile per turn in one of the following directions:"
				+ "'x-axis increase' -> increase x by 1"
				+ "'x-axis decrease' -> decrease x by 1"
				+ "'y-axis increase' -> increase y by 1"
				+ "'y-axis decrease' -> decrease y by 1"
				+ "Your goal is to get as close as possible to the player, measured by Manhattan distance (shortest Manhattan distance)."
				+ "Now, based on your current position ("+bossX+", "+bossY+"), what is the correct move?"
				+ "Only reply with the increase text ('x-axis increase', 'x-axis decrease', 'y-axis increase', or 'y-axis decrease'). ONLY THAT, no other text";
		
		URL url = new URL("http://localhost:11434/api/generate");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setDoOutput(true);
		
		String jsonInputString = String.format(
					"{\"model\": \"%s\", \"prompt\":\"%s\", \"stream\": false}", modelName, promptText);
		
		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}
			
		int code = conn.getResponseCode();
		//System.out.println("Codigo de respuesta: " + code);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
		
		//System.out.println("Cuerpo de respuesta: " + response.toString()); Debugging
		
		JSONObject jsonResponse = new JSONObject(response.toString());
		String responseText = jsonResponse.getString("response");
		//System.out.println("Respuesta: " + responseText); //Debugging
		
		conn.disconnect();
		
		return responseText;
		}
		catch (IOException e) {
			return "";
		}
		

	}
}
