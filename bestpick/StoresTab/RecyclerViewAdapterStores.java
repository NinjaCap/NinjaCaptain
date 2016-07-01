package com.bestpickindia.android.bestpick.StoresTab;

/**
 * Created by HP on 6/21/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestpickindia.android.bestpick.Favourites.FavouritesFragment;
import com.bestpickindia.android.bestpick.Feedback.FeedbackFragment;
import com.bestpickindia.android.bestpick.Help.HelpFragment;
import com.bestpickindia.android.bestpick.Invite.InviteFragment;
import com.bestpickindia.android.bestpick.MainActivity;
import com.bestpickindia.android.bestpick.Notifications.NotificationsFragment;
import com.bestpickindia.android.bestpick.Profile.ProfileFragment;
import com.bestpickindia.android.bestpick.R;


public class RecyclerViewAdapterStores extends RecyclerView.Adapter<RecyclerViewAdapterStores.ViewHolder> {
    String[] titles;
    TypedArray icons;
    Context context;

    // The default constructor to receive titles,icons and context from MainActivity.
    RecyclerViewAdapterStores(String[] titles, TypedArray icons, Context context){

        this.titles = titles;
        this.icons = icons;
        this.context = context;
    }

    /**
     *Its a inner class to RecyclerViewAdapterStores Class.
     *This ViewHolder class implements View.OnClickListener to handle click events.
     *If the itemType==1 ; it implies that the view is a single row_item with TextView and ImageView.
     *This ViewHolder describes an item view with respect to its place within the RecyclerView.
     *For every item there is a ViewHolder associated with it .
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView  navTitle;
        ImageView navIcon;
        Context context;
        FragmentTransaction fragmentTransaction;
        public ViewHolder(View drawerItem , int itemType , Context context){

            super(drawerItem);
            this.context = context;
            drawerItem.setOnClickListener(this);
            if(itemType==1){
                navTitle = (TextView) itemView.findViewById(R.id.tv_NavTitle);
                navIcon = (ImageView) itemView.findViewById(R.id.iv_NavIcon);
            }
        }

        /**
         *This defines onClick for every item with respect to its position.
         */

        @Override
        public void onClick(View v) {

            StoreShow storeActivity = (StoreShow)context;
            storeActivity.drawerLayout.closeDrawers();
            if(getPosition()!=1 ) {
                fragmentTransaction = storeActivity.getSupportFragmentManager().beginTransaction();
            }
            switch (getPosition()){
                case 1:
                    Intent i = new Intent(context, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                case 2:
                    Fragment profileFragment = new InviteFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,profileFragment);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    Fragment notificationsFragment = new NotificationsFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,notificationsFragment);
                    fragmentTransaction.commit();
                    break;
                case 4:
                    Fragment favouritesFragment = new FavouritesFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,favouritesFragment);
                    fragmentTransaction.commit();
                    break;
                case 5:
                    Fragment inviteFragment = new InviteFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,inviteFragment);
                    fragmentTransaction.commit();
                    break;
                case 6:
                    Fragment helpFragment = new HelpFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,helpFragment);
                    fragmentTransaction.commit();
                    break;
                case 7:
                    Fragment feedbackFragment = new FeedbackFragment();
                    fragmentTransaction.replace(R.id.containerViewStores,feedbackFragment);
                    fragmentTransaction.commit();
                    break;
                case 8:
                   // Fragment logoutFragment = new LogoutFragment();
                   // fragmentTransaction.replace(R.id.containerView,tableFragment);
                   // fragmentTransaction.commit();
                    break;

            }
        }
    }

    /**
     *This is called every time when we need a new ViewHolder and a new ViewHolder is required for every item in RecyclerView.
     *Then this ViewHolder is passed to onBindViewHolder to display items.
     */

    @Override
    public RecyclerViewAdapterStores.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType==1){
            View itemLayout =   layoutInflater.inflate(R.layout.drawer_item_layout,null);
            return new ViewHolder(itemLayout,viewType,context);
        }
        else if (viewType==0) {
            View itemHeader = layoutInflater.inflate(R.layout.header_layout,null);
            return new ViewHolder(itemHeader,viewType,context);
        }



        return null;
    }

    /**
     *This method is called by RecyclerView.Adapter to display the data at the specified position.
     *This method should update the contents of the itemView to reflect the item at the given position.
     *So here , if position!=0 it implies its a row_item and we set the title and icon of the view.
     */

    @Override
    public void onBindViewHolder(RecyclerViewAdapterStores.ViewHolder holder, int position) {

        if(position!=0){
            holder.navTitle.setText(titles[position - 1]);
            holder.navIcon.setImageResource(icons.getResourceId(position-1,-1));
        }

    }

    /**
     *It returns the total no. of items . We +1 count to include the header view.
     *So , it the total count is 5 , the method returns 6.
     *This 6 implies that there are 5 row_items and 1 header view with header at position zero.
     */

    @Override
    public int getItemCount() {
        return titles.length+1;
    }


    /**
     *This methods returns 0 if the position of the item is '0'.
     *If the position is zero its a header view and if its anything else
     *its a row_item with a title and icon.
     */

    @Override
    public int getItemViewType(int position) {
        if(position==0)return 0;
        else return 1;
    }


}
