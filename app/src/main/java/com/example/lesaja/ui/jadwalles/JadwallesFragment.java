package com.example.lesaja.ui.jadwalles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.lesaja.R;
import com.example.lesaja.tab.Rencana;
import com.example.lesaja.tab.Riwayat;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class JadwallesFragment extends Fragment {

	private JadwallesViewModel jadwallesViewModel;
    private TabLayout tabLayout;
    public ViewPager viewPager;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		jadwallesViewModel =
				ViewModelProviders.of(this).get(JadwallesViewModel.class);
		View root = inflater.inflate(R.layout.fragment_jadwalles, container, false);

        viewPager = (ViewPager) root.findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

		return root;
	}
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new Rencana(), "Rencana");
        adapter.addFragment(new Riwayat(), "Riwayat");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}