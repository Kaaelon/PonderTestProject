package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import testproject.halfmoonstudios.com.ponder.R;

public class MenuFragment extends Fragment {

    private ImageView histView;
    private ImageView popView;
    private ImageView litView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu,parent,false);
        //Could be a problem moving into seperate method

        //Assigns values to buttons
        histView = (ImageView)v.findViewById(R.id.historyView);
        popView = (ImageView)v.findViewById(R.id.popView);
        litView = (ImageView)v.findViewById(R.id.litView);

        histView.setOnClickListener(new View.OnClickListener(){

                                        @Override
                                    public void onClick(View v){




                                        }

                                    }
        );
        return v;
    }

    public void drawGUI(){

    }


}
