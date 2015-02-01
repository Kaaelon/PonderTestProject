package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuFragment extends Fragment {
    //Declares imageviews
    private ImageView mWellbeingView;
    private ImageView mGriefView;
    private ImageView mHealthView;
    private ImageView mIdeasView;
    private ImageView mMotivationView;
    //Declares textviews
    private TextView mWellbeingText;
    private TextView mGriefText;
    private TextView mHealthText;
    private TextView mIdeasText;
    private TextView mMotivationText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu,parent,false);
        //Could be a problem moving into seperate method

        //Assigns values to ImageViews
       mWellbeingView = (ImageView)v.findViewById(R.id.wellbeingView);
       mGriefView = (ImageView)v.findViewById(R.id.griefView);
       mHealthView = (ImageView)v.findViewById(R.id.healthView);
       mIdeasView = (ImageView)v.findViewById(R.id.ideasView);
       mMotivationView = (ImageView)v.findViewById(R.id.motivationView);

        //Assigns values to TextViews
        mWellbeingText = (TextView)v.findViewById(R.id.wellbeingText);
        mGriefText = (TextView)v.findViewById(R.id.griefText);
        mHealthText = (TextView)v.findViewById(R.id.healthText);
        mIdeasText = (TextView)v.findViewById(R.id.ideasText);
        mMotivationText = (TextView)v.findViewById(R.id.motivationText);

        //Create TypeFaceObject and assign font
        Typeface mtypeFace = Typeface.createFromAsset(getActivity().getResources().getAssets(),"futuralight.ttf");

        //Sets text of TextViews
        mWellbeingText.setTypeface(mtypeFace);
        mGriefText.setTypeface(mtypeFace);
        mHealthText.setTypeface(mtypeFace);
        mIdeasText.setTypeface(mtypeFace);
        mMotivationText.setTypeface(mtypeFace);

        //Set onclick listeners for textviews
        setListeners();
        return v;
    }

    public void setListeners(){
     mWellbeingView.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v){

            }
        });

    mGriefView.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){

        }
    });

     mHealthView.setOnClickListener(new View.OnClickListener(){

       @Override
       public void onClick(View v){

            }
        });

     mIdeasView.setOnClickListener(new View.OnClickListener(){

       @Override
       public void onClick(View v){

            }
        });
     mMotivationView.setOnClickListener(new View.OnClickListener(){

     @Override
     public void onClick(View v){

            }
        });
    }


}
