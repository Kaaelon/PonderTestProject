package testproject.halfmoonstudios.com.ponder;

import android.app.Fragment;

/**
 * Created by Michae on 2/19/2015.
 */
public class BaseFragment extends Fragment {

    public void actionBarItemHighlighted(){

        //Call actionBarItemSelected from mainActivity and passes in the name of this current class *used in more than one class possibly worth implementing inheritance
        ((MainActivity)getActivity()).actionBarItemSelected(this.getClass().getName());

    }

    public void setCategoryVisibilty(float visibilty){
        //Calls mainActivity activities setCategoryVisibilty() method which calls FragmentActionBar to set the visibility of CategoryView, pass 1 for visible 0 for invisible
        ((MainActivity)getActivity()).setCategoryVisibility(visibilty);
    }

}
