package com.example.lesaja.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lesaja.MainActivity;
import com.example.lesaja.R;
import com.example.lesaja.RegisActivity;
import com.example.lesaja.TambahLesActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static final String EXTRA_MESSAGEE = "hmm";
    private String nama;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton button1 = (ImageButton) root.findViewById(R.id.imageButton3);
        ImageButton button2 = (ImageButton) root.findViewById(R.id.imageButton4);
        ImageButton button3 = (ImageButton) root.findViewById(R.id.imageButton5);
        ImageButton button4 = (ImageButton) root.findViewById(R.id.imageButton6);
        ImageButton button5 = (ImageButton) root.findViewById(R.id.imageButton8);
        ImageButton button6 = (ImageButton) root.findViewById(R.id.imageButton12);
        ImageButton button7 = (ImageButton) root.findViewById(R.id.imageButton);
        ImageButton button8 = (ImageButton) root.findViewById(R.id.imageButton7);
        ImageButton button9 = (ImageButton) root.findViewById(R.id.imageButton9);
        ImageButton button10 = (ImageButton) root.findViewById(R.id.imageButton10);
        ImageButton button11 = (ImageButton) root.findViewById(R.id.imageButton11);
        ImageButton button12 = (ImageButton) root.findViewById(R.id.imageButton13);
        ImageButton button13 = (ImageButton) root.findViewById(R.id.imageButton14);
        ImageButton button14 = (ImageButton) root.findViewById(R.id.imageButton15);
        ImageButton button15 = (ImageButton) root.findViewById(R.id.imageButton16);
        ImageButton button16 = (ImageButton) root.findViewById(R.id.imageButton17);
        ImageButton button17 = (ImageButton) root.findViewById(R.id.imageButton18);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="IPA-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="IPS-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Bahasa Indonesia-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Bahasa Inggris-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Matematika-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="TIK-SD";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Fisika-SMP";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Biologi-SMP";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Matematika-SMP";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Bahasa Inggris-SMP";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="TIK-SMP";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Fisika-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button13.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Biologi-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button14.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Kimia-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button15.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Bahasa Inggris-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button16.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="TIK-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });

        button17.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nama="Matematika-SMA";
                Intent intent = new Intent(getActivity(), TambahLesActivity.class);
                intent.putExtra(EXTRA_MESSAGEE,nama);
                startActivity(intent);
            }
        });
        return root;
    }

}