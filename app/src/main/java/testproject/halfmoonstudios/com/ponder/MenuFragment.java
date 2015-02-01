package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import testproject.halfmoonstudios.com.ponder.R;

public class MenuFragment extends Fragment {

    private ImageView mWellbeingView;
    private ImageView mGriefView;
    private ImageView mHealthView;
    private ImageView mIdeasView;
    private ImageView mMotivationView;
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
       mWellbeingView = (ImageView)v.findViewById(R.id.wellbeingView);
       mGriefView = (ImageView)v.findViewById(R.id.griefView);
       mHealthView = (ImageView)v.findViewById(R.id.healthView);
       mIdeasView = (ImageView)v.findViewById(R.id.ideasView);
       mMotivationView = (ImageView)v.findViewById(R.id.motivationView);

        mWellbeingView.setOnClickListener(new View.OnClickListener(){

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
