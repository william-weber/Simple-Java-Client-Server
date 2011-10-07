import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 * Class containing methods for taking the command string (a string with a digit 1-6)
 * received from the server and converting that into its respective Linux shell command, 
 * then executing it and returning the results.
 */
public class CommandExecutor {
	
	/**
	 * Runs the shell command after first using parseCommand() to determine which
	 * command to run.
	 * 
	 * @param commandString		A string containing a single digit, 1-6;
	 * @return					A string containing the results of the shell command.
	 */
	static String run(String commandString) {
		String result = "";
		String line;
		try {
		    Process child = Runtime.getRuntime().exec(parseCommand(commandString));

		    BufferedReader output = new BufferedReader(new InputStreamReader(child.getInputStream()));
		    while ((line = output.readLine()) != null) {
		    	result = result.concat(line);
			result = result.concat("\n");
		    }
		    
		result = result.concat("\n");
		result = result.concat("END_MESSAGE");
		    output.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Converts the digit string into its respective shell command.
	 * 
	 * @param inputString		A string containing a single digit, 1-6;
	 * @return					
	 */
	static String parseCommand(String inputString) {
		int inputInt = Integer.parseInt(inputString);
		String commandString = "";
		switch (inputInt) {
		// Date
		case 1:
			commandString = "date";
			break;
			
		// Uptime
		case 2:
			commandString = "uptime";
			break;
		
		// Memory use
		case 3:
			commandString = "free";
			break;
			
		// netstat
		case 4:
			commandString = "netstat";
			break;
			
		// current users
		case 5:
			commandString = "who";
			break;
			
		// running processes
		case 6:
			commandString = "ps -e";
			break;
		}
		
		return commandString;
	}
}
