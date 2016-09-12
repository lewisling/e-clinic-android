package sg.edu.ntu.cz3002.enigma.eclinic.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sg.edu.ntu.cz3002.enigma.eclinic.R;

/**
 * Created by koAllen on 9/2/2016.
 */
public class ChatFragment extends Fragment {

    private static final String TAG = "ChatFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    public static ChatFragment newInstance(int index) {
        ChatFragment f = new ChatFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }
}
