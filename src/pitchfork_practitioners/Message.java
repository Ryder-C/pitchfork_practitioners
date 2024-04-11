package pitchfork_practitioners;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Message {
    private String text;
    private String senderID;
    private String receiverID;

    public Message(String text, String senderID, String receiverID) {
        this.text = text;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public void saveMessage(File messageRecord) throws IOException {
        FileWriter writer = new FileWriter(messageRecord, true);
        
            Database.saveValue(writer, senderID, text);
               
        writer.close();
    }

    public static Message[] loadAllMessages(File messageRecord, String viewerID, String otherID) throws IOException {
        if (!messageRecord.isFile()) {
            throw new IOException("Message record file not found.");
        }

        Scanner scanner = new Scanner(messageRecord);
        ArrayList<Message> messages = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] temp = line.split(":");
            if (temp.length < 2) {
                continue;
            }
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
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public String getText() {
        return text;
    }
}
