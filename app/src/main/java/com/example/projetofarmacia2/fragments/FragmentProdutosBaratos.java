package com.example.projetofarmacia2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.api_manipulacao.Busca_medicametos_mais_baratos;


public class FragmentProdutosBaratos extends Fragment {

    View view;
    Spinner spinner;
    ImageView voltar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment_produtos_baratos,container,false);

        voltar = (ImageView) view.findViewById(R.id.img_voltar);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*Busca_produtos2 busca_produtos = new Busca_produtos2();
        busca_produtos.buscarProdutos(getContext(),getActivity(),view,1);*/
        
        String medicamento = null;

        Bundle mBundle = new Bundle();
        if(mBundle != null){
            mBundle = getArguments();
            medicamento = mBundle.getString("medicamento");

    }

        Busca_medicametos_mais_baratos busca_medicametos_mais_baratos = new Busca_medicametos_mais_baratos();
        busca_medicametos_mais_baratos.buscarProdutos(getContext(),getActivity(),getView(),medicamento);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }




}
