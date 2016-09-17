package sg.edu.ntu.cz3002.enigma.eclinic.fragment;

/**
 * Created by ZWL on 12/9/16.
 */
public class ChatMessage {
    public boolean _mine;
    public String _message, _sender, _receiver;

    public ChatMessage(boolean mine, String message, String sender, String receiver){
        super();
        this._mine = mine;
        this._message = message;
        this._receiver = receiver;
        this._sender = sender;
    }
    public void setMine(boolean mine){
        this._mine = mine;
    }
    public boolean getMine(){
        return this._mine;
    }
    public void setSender(String sender){
        this._sender = sender;
    }
    public String getSender(){
        return this._sender;
    }
}
