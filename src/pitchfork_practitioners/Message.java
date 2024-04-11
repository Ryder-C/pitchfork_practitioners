package pitchfork_practitioners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Message {
	private String text;
	private String sender_id;
	private String receiver_id;
	
	public Message(String text, String sender_id, String receiver_id) {
		this.text = text;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
	}
	
	public void saveMessage(File messageRecord) throws IOException {
		FileWriter writer = new FileWriter(messageRecord, true);
		Database.saveValue(writer, sender_id, text);
		writer.close();
	}
	
	public static Message[] loadAllMessages(File messageRecord, String viewerID, String otherID) throws IOException {
		if (!messageRecord.isFile()) {
			throw new IOException();
		}
		
		Scanner scanner = new Scanner(messageRecord);
		ArrayList<Message> messages = new ArrayList<Message>();
		while (scanner.hasNextLine()) {
			String text = scanner.nextLine();
			String[] temp = text.split(": ");
			String id = temp[0].strip();
			String data = temp[1].strip();
			
			if (id.equals(viewerID)) {
				messages.add(new Message(data, viewerID, otherID));
			} else {
				messages.add(new Message(data, otherID, viewerID));
			}
		}
		scanner.close();
		return messages.toArray(new Message[messages.size()]);
	}
	
	public String getSenderID() {
        return sender_id;
    }
	
	public String getReceiverID() {
        return receiver_id;
    }

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
