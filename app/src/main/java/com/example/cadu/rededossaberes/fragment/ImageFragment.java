package com.example.cadu.rededossaberes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.adapter.ImageAdapter;
import com.parse.ParseObject;
import java.util.ArrayList;

public class ImageFragment extends Fragment {
    private ListView imagens;
    private ArrayList<ParseObject> descricoesImagens;
    private ArrayAdapter<ParseObject> adapterArray;

    public ImageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imagens = view.findViewById(R.id.imagens);
        adapterArray = new ImageAdapter(getActivity(),descricoesImagens);
        imagens.setAdapter(adapterArray);

        getPostagens();
        return view;
    }
    private void getPostagens()
    {

    }
}
