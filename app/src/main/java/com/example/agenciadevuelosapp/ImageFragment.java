package com.example.agenciadevuelosapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {

    private static final String ARG_IMAGE_RESOURCE = "imageResource";
    private int imageResource;

    public ImageFragment() {
        // Constructor vacío requerido.
    }

    public static ImageFragment newInstance(int imageResource) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RESOURCE, imageResource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageResource = getArguments().getInt(ARG_IMAGE_RESOURCE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_image_fragment, container, false);
        ImageView imageView = rootView.findViewById(R.id.imageView);

        // Configura la vista para mostrar la imagen en el ImageView.
        imageView.setImageResource(imageResource);

        return rootView;
    }
}
