package org.themoviedb.catalog.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.themoviedb.catalog.R;
import org.themoviedb.catalog.ui.favorites.FavoritesFragment;
import org.themoviedb.catalog.ui.movies.MoviesFragment;
import org.themoviedb.catalog.util.Logic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainFragment extends Fragment {

    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        pager = layout.findViewById(R.id.pager);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager.setAdapter(new PagerAdapter(this));
        Logic.checkNotNull(getActivity());
        getActivity().setTitle(R.string.app_name);
    }

    private static class PagerAdapter extends FragmentPagerAdapter {

        private final Context context;

        PagerAdapter(@NonNull Fragment fragment) {
            super(fragment.getChildFragmentManager());
            Logic.checkNotNull(fragment.getContext());
            context = fragment.getContext().getApplicationContext();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return position == 0 ? new MoviesFragment() : new FavoritesFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return context.getString(position == 0 ? MoviesFragment.TITLE_RES_ID : FavoritesFragment.TITLE_RES_ID);
        }
    }
}
